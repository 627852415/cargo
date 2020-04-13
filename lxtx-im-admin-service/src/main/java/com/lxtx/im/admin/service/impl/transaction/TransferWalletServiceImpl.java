package com.lxtx.im.admin.service.impl.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TransferWalletFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.TransferWalletDetailReq;
import com.lxtx.im.admin.service.request.TransferWalletReq;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.transaction.TransferWalletService;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author qing
 * @Description 钱包转账
 * @date: 2019年11月20日 下午6:23:27
 */
@Slf4j
@Service
public class TransferWalletServiceImpl implements TransferWalletService {

    // 钱包转账
    private static final String APPLY_LIST_FILENAME = "钱包转账列表";

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    // 表状态：status=（【1：待处理，2：处理中，3：已提交，4：成功，5:失败】）
    // 页面展示状态：status=（1：处理中（1,2,3），2：成功（4），3：失败（5））
    private static final String[] STATUSVALUES = {"处理中", "处理中", "处理中", "成功", "失败"};
    private static final String[] TYPEVALUES = {"是", "否"};

    // 导出数据要加数据数量限制，数量值字典全局配置（暂定30000）条
    // 导出数据条限制
    private static Integer EX_PAGE_SIZE = 1000000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private WalletUserFeign walletUserFeign;

    @Autowired
    private CoinService coinService;

    @Autowired
    private TransferWalletFeign transferWalletFeign;

    @Autowired
    private DictService dictService;

    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(TransferWalletReq req, HttpSession session) {
        FeignTransferWalletReq feignReq = new FeignTransferWalletReq();
        // 转出用户昵称换取用户钱包ID 且与 转出用户钱包ID进行条件过滤
        if (!StringUtils.isEmpty(req.getUserName())) {
            List<String> userIds = getUserIds(req.getUserName());
            if (CollectionUtils.isEmpty(userIds)) {
                return BaseResult.success(new Page<>());
            }
            if (!StringUtils.isEmpty(req.getUserId())) {
                if (!userIds.contains(req.getUserId())) {
                    return BaseResult.success(new Page<>());
                }
                userIds = new ArrayList<>();
                userIds.add(req.getUserId());
            }
            feignReq.setUserIds(userIds);
        }
        // 有转出用户钱包ID，无转出用户昵称
        if (CollectionUtils.isEmpty(feignReq.getUserIds()) && !StringUtils.isEmpty(req.getUserId())) {
            List<String> userIds = new ArrayList<>();
            userIds.add(req.getUserId());
            feignReq.setUserIds(userIds);
        }
        BeanUtils.copyProperties(req, feignReq);
        Integer isInnerAccount = req.getIsInnerAccount();
        if (isInnerAccount != null) {
            if (isInnerAccount == 0) {
                feignReq.setIsInnerAccount(true);
            }
            if (isInnerAccount == 1) {
                feignReq.setIsInnerAccount(false);
            }
        }
        boolean isKHShowAccount = userService.isShowAccount();
        feignReq.setKHShowAccount(isKHShowAccount);
        if (isKHShowAccount) {
            BaseResult baseResult = userFeign.selectKhUser();
            if (baseResult.isSuccess() && null != baseResult.getData()) {
                List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                feignReq.setKhUserAccountList(userAccountList);
            }
        }

        BaseResult result = transferWalletFeign.listPage(feignReq);
        if (result.isSuccess() && result.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            List<TransferWalletResp> respList = JSONObject.parseArray(jsonObject.getString("records"),
                    TransferWalletResp.class);
            if (!CollectionUtils.isEmpty(respList)) {
                // 设置数据内部属性
                resetRespList(respList);
            }
            // 覆盖返回
            jsonObject.put("records", respList);
            result.setData(jsonObject);
        }
        return result;
    }

    @Override
    public BaseResult downloadLock(TransferWalletReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_WALLET_LOCK.concat(userId);
        String requestId = session.getId();
        boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!getRechargeDownloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if (requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {
            //判断搜索结果total值
            req.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req, session).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize);
            if (total > maxPageSizeLong) {
                if (getRechargeDownloadLock)
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if (getRechargeDownloadLock)
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public void downloadList(TransferWalletReq req, HttpSession session, HttpServletResponse response) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_WALLET_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            List<TransferWalletResp> respList = null;
            FeignTransferWalletReq feignReq = new FeignTransferWalletReq();
            // 转出用户昵称换取用户钱包ID 且与 转出用户钱包ID进行条件过滤
            if (!StringUtils.isEmpty(req.getUserName())) {
                List<String> userIds = getUserIds(req.getUserName());
                if (CollectionUtils.isEmpty(userIds)) {
                    return;
                }
                if (!StringUtils.isEmpty(req.getUserId())) {
                    if (!userIds.contains(req.getUserId())) {
                        return;
                    }
                    userIds = new ArrayList<>();
                    userIds.add(req.getUserId());
                }
                feignReq.setUserIds(userIds);
            }
            // 有转出用户钱包ID，无转出用户昵称
            if (CollectionUtils.isEmpty(feignReq.getUserIds()) && !StringUtils.isEmpty(req.getUserId())) {
                List<String> userIds = new ArrayList<>();
                userIds.add(req.getUserId());
                feignReq.setUserIds(userIds);
            }
            BeanUtils.copyProperties(req, feignReq);
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            if (userService.isShowAccount()) {
                BaseResult baseResult = userFeign.selectKhUser();
                if (baseResult.isSuccess() && null != baseResult.getData()) {
                    List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                    feignReq.setKhUserAccountList(userAccountList);
                }
            }

            BaseResult result = transferWalletFeign.list(feignReq);
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("list"), TransferWalletResp.class);
                if (!CollectionUtils.isEmpty(respList)) {
                    // 设置数据内部属性
                    resetRespList(respList);
                }
            }
            if (respList == null) {
                respList = new ArrayList<>();
            }
            // 导出文件
            ExcelUtil.exportExcel(response, respList,
                    getPlatformWithdrawApplyDownloadFileName(req.getCreateTimeStart(), req.getCreateTimeEnd()),
                    APPLY_LIST_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public String detail(TransferWalletDetailReq req, Model model) {
        TransferWalletResp resp = null;
        FeignTransferWalletReq feignReq = new FeignTransferWalletReq();
        feignReq.setId(req.getId());
        BaseResult result = transferWalletFeign.listPage(feignReq);
        if (result.isSuccess() && result.getData() != null) {
            List<TransferWalletResp> respList = null;
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            respList = JSONObject.parseArray(jsonObject.getString("records"), TransferWalletResp.class);
            if ((!CollectionUtils.isEmpty(respList)) && respList.size() > 0) {
                // 设置数据内部属性
                resetRespList(respList);
                resp = respList.get(0);
            }
        }
        model.addAttribute("detail", resp);
        return "transaction/transfer-wallet-detail";
    }

    /**
     * @param userName
     * @return
     * @Description 获取用户账号ID换取钱包ID
     */
    private List<String> getUserIds(String userName) {
        // 根据用户名称查询指定用户
        FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
        usernameReq.setName(userName);
        BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
        if (queryByUsername.isSuccess() && queryByUsername.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUsername.getData()));
            List<String> accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            if (!CollectionUtils.isEmpty(accountIds)) {
                FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
                registerUserReq.setAccounts(accountIds);
                BaseResult registerWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
                if (registerWalletUser.isSuccess() && registerWalletUser.getData() != null) {
                    jsonObject = JSONObject.parseObject(JSON.toJSONString(registerWalletUser.getData()));
                    List<String> userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                    if (!CollectionUtils.isEmpty(userIds)) {
                        return userIds;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param userIds
     * @return
     * @Description 获取用户名集合
     */
    private Map<String, String> getUserNameMap(HashSet<String> userIds) {
        Map<String, String> userNameMap = new HashMap<>();
        //根据钱包ID兑换accountId
        HashSet<String> accountIds = new HashSet<>();
        Map<String, String> accountIdsMap = new HashMap<>();
        FeignTransferUserReq req = new FeignTransferUserReq();
        req.setUserIdList(new ArrayList<>(userIds));
        BaseResult coreResult = transferWalletFeign.listTransferUserNames(req);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
            List<WalletUserResp> walletUserResps = JSONObject.parseArray(jsonObject.getString("list"), WalletUserResp.class);
            if (!CollectionUtils.isEmpty(walletUserResps)) {
                walletUserResps.stream().forEach(record -> {
                    accountIdsMap.put(record.getId(), record.getPlatformUserId());
                    accountIds.add(record.getPlatformUserId());
                });
                // 查询符合条件的用户信息并转Map
                Map<String, String> userIdsMap = new HashMap<>();
                FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
                queryUserListReq.setIds(new ArrayList<>(accountIds));
                coreResult = userFeign.queryList(queryUserListReq);
                if (coreResult.isSuccess() && coreResult.getData() != null) {
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()),
                            UserListResp.class);
                    List<UserDetailResp> userDetailResps = userListResp.getList();
                    if (!CollectionUtils.isEmpty(userDetailResps)) {
                        userDetailResps.stream().forEach(record -> {
                            userIdsMap.put(record.getAccount(), record.getName());
                        });
                        userIds.stream().forEach(userId -> {
                            userNameMap.put(userId, userIdsMap.get(accountIdsMap.get(userId)));
                        });
                    }
                }
            }
        }
        return userNameMap;
    }

    /**
     * @return
     * @Description 获取币种名称集合
     */
    private Map<String, String> getCoinNameMap() {
        Map<String, String> coinNameMap = new HashMap<>();
        List<CoinResp> coinResps = coinService.getAllCoinList();
        if (!CollectionUtils.isEmpty(coinResps)) {
            coinResps.stream().forEach(crs -> {
                coinNameMap.put(crs.getId(), crs.getCoinName());
            });
        }
        return coinNameMap;
    }

    private Map<String, String> getAliasNameCoinNameMap(boolean isKHAccount) {
        Map<String, String> coinNameMap = new HashMap<>();
        List<CoinResp> coinResps;
        if (isKHAccount) {
            coinResps = coinService.getAllLegalCoinList();
        } else {
            return getCoinNameMap();
        }
        if (!CollectionUtils.isEmpty(coinResps)) {
            coinResps.stream().forEach(crs -> {
                coinNameMap.put(crs.getId(), crs.getAliasName());
            });
        }
        return coinNameMap;
    }

    /**
     * @param respList
     * @Description 设置数据内部属性
     */
    private void resetRespList(List<TransferWalletResp> respList) {
        HashSet<String> userIds = new HashSet<>();
        // 获取钱包ID去重集合
        respList.stream().forEach(resp -> {
            if (!StringUtils.isEmpty(resp.getUserId())) {
                userIds.add(resp.getUserId());
            }
        });
        // 根据钱包ID集合获取用户昵称集合
        Map<String, String> userNameMap = getUserNameMap(userIds);
        // 获取币种名称集合
        Map<String, String> coinNameMap = getAliasNameCoinNameMap(userService.isShowAccount());
        // 设置转出用户昵称、接收用户昵称
        respList.stream().forEach(resp -> {
            resp.setUserName(userNameMap.get(resp.getUserId()));
            resp.setCoinName(coinNameMap.get(resp.getCoinId()));
            resp.setStatusVaule(STATUSVALUES[resp.getStatus() - 1]);
            resp.setTypeVaule(TYPEVALUES[resp.getType() - 2]);
            ;
        });
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     * @Description 命名文件名
     */
    private String getPlatformWithdrawApplyDownloadFileName(Date startTime, Date endTime) {
        String fileName = APPLY_LIST_FILENAME;
        if (startTime != null) {
            if (endTime != null) {
                fileName = fileName + DATEFORMAT.format(startTime) + "至" + DATEFORMAT.format(endTime);
            } else {
                fileName = fileName + DATEFORMAT.format(startTime) + "至" + DATEFORMAT.format(new Date());
            }
        } else {
            if (endTime != null) {
                fileName = fileName + "至" + DATEFORMAT.format(endTime);
            } else {
                fileName = fileName + DATEFORMAT.format(new Date());
            }
        }
        return fileName;
    }
}
