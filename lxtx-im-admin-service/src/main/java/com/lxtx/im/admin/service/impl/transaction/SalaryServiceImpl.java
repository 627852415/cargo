package com.lxtx.im.admin.service.impl.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.FeignSalaryInDetailReq;
import com.lxtx.im.admin.feign.feign.TransferWalletFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.SalaryInDetailReq;
import com.lxtx.im.admin.service.request.SalaryInPageListReq;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.transaction.SalaryService;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2019/12/13 15:08
 */
@Service
@Slf4j
public class SalaryServiceImpl implements SalaryService {
    // 扫码付款
    private static final String SALARY_IN_FILENAME = "代发工资转入交易";

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_YYYY_MM_DD);
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private TransferWalletFeign transferWalletFeign;
    @Autowired
    private CoinService coinService;
    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private DictService dictService;
    // 导出数据要加数据数量限制，数量值字典全局配置（暂定50000）条
    // 导出数据条限制
    public static Integer EX_PAGE_SIZE = 50000;

    //锁时间 60秒
    public static Integer LOCK_TIME = 60000;
    private static final String[] STATUSVALUES = { "初始化", "处理中","成功","失败"};


    @Override
    public BaseResult salaryInPageList(SalaryInPageListReq req) {
        List<String> userIds = new ArrayList<>();
        //昵称不为空
        if (StringUtils.isNotBlank(req.getUserName())) {
            //根据昵称获取用户id
            userIds = getUserIds(req.getUserName());
            if (CollectionUtils.isEmpty(userIds)) {
                return BaseResult.success(new Page<>());
            }
            if (StringUtils.isNotBlank(req.getUserId())) {
                if (!userIds.contains(req.getUserId())) {
                    return BaseResult.success(new Page<>());
                }
            }
        }else{
            if (StringUtils.isNotBlank(req.getUserId())) {
                userIds.add(req.getUserId());
            }
        }
        FeignSalaryInPageListReq feignReq = new FeignSalaryInPageListReq();
        BeanUtils.copyProperties(req,feignReq);
        feignReq.setUserIds(userIds);
        BaseResult result = transferWalletFeign.salaryInPageList(feignReq);
        if (result.isSuccess() && result.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            List<SalaryInResp> respList = JSONObject.parseArray(jsonObject.getString("records"),SalaryInResp.class);
            if (CollectionUtils.isNotEmpty(respList)) {
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
    public BaseResult salaryOutPageList(SalaryInPageListReq req) {
        List<String> userIds = new ArrayList<>();
        //昵称不为空
        if (StringUtils.isNotBlank(req.getUserName())) {
            //根据昵称获取用户id
            userIds = getUserIds(req.getUserName());
            if (CollectionUtils.isEmpty(userIds)) {
                return BaseResult.success(new Page<>());
            }
            if (StringUtils.isNotBlank(req.getUserId())) {
                if (!userIds.contains(req.getUserId())) {
                    return BaseResult.success(new Page<>());
                }
            }
        }else{
            if (StringUtils.isNotBlank(req.getUserId())) {
                userIds.add(req.getUserId());
            }
        }
        FeignSalaryInPageListReq feignReq = new FeignSalaryInPageListReq();
        BeanUtils.copyProperties(req,feignReq);
        feignReq.setUserIds(userIds);
        BaseResult result = transferWalletFeign.salaryOutPageList(feignReq);
        if (result.isSuccess() && result.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            List<SalaryInResp> respList = JSONObject.parseArray(jsonObject.getString("records"),SalaryInResp.class);
            if (CollectionUtils.isNotEmpty(respList)) {
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
    public String salaryInDetail(SalaryInDetailReq req, Model model) {
        FeignSalaryInDetailReq feignReq = new FeignSalaryInDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult result = transferWalletFeign.salaryInDetail(feignReq);
        SalaryInResp resp = new SalaryInResp();
        if (result.isSuccess() && result.getData() != null) {
            resp = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), SalaryInResp.class);
            HashSet<String> userIds = new HashSet<>();
            userIds.add(resp.getUserId());
            // 根据钱包ID集合获取用户昵称集合
            Map<String, String> userNameMap = getUserNameMap(userIds);
            // 设置用户昵称
            resp.setUserName(userNameMap.get(resp.getUserId()));
            result.setData(resp);
        }
        model.addAttribute("detail", resp);
        return "transaction/transfer-salary-in-detail";
    }

    @Override
    public String salaryOutDetail(SalaryInDetailReq req, Model model) {
        FeignSalaryInDetailReq feignReq = new FeignSalaryInDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult result = transferWalletFeign.salaryOutDetail(feignReq);
        SalaryInResp resp = new SalaryInResp();
        if (result.isSuccess() && result.getData() != null) {
            resp = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), SalaryInResp.class);
            HashSet<String> userIds = new HashSet<>();
            userIds.add(resp.getUserId());
            // 根据钱包ID集合获取用户昵称集合
            Map<String, String> userNameMap = getUserNameMap(userIds);
            // 设置用户昵称
            resp.setUserName(userNameMap.get(resp.getUserId()));
            result.setData(resp);
        }
        model.addAttribute("detail", resp);
        return "transaction/transfer-salary-in-detail";
    }


    @Override
    public BaseResult salaryInDownloadLock(SalaryInPageListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_SALARY_IN_LOCK.concat(userId);
        String requestId = session.getId();
        boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!getRechargeDownloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if(requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {
            //判断搜索结果total值
            req.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(salaryInPageList(req).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize==null?EX_PAGE_SIZE:Integer.valueOf(maxPageSize);
            if(total>maxPageSizeLong) {
                if(getRechargeDownloadLock)
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if(getRechargeDownloadLock)
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public BaseResult salaryOutDownloadLock(SalaryInPageListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_SALARY_IN_LOCK.concat(userId);
        String requestId = session.getId();
        boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!getRechargeDownloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if(requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {
            //判断搜索结果total值
            req.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(salaryOutPageList(req).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize==null?EX_PAGE_SIZE:Integer.valueOf(maxPageSize);
            if(total>maxPageSizeLong) {
                if(getRechargeDownloadLock)
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if(getRechargeDownloadLock)
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public void salaryInDownloadList(SalaryInPageListReq req, HttpSession session, HttpServletResponse response) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_SALARY_IN_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            List<SalaryInResp> respList = null;
            FeignSalaryInPageListReq feignReq = new FeignSalaryInPageListReq();
            if (!org.springframework.util.StringUtils.isEmpty(req.getUserName())) {
                List<String> userIds = getUserIds(req.getUserName());
                if (org.springframework.util.CollectionUtils.isEmpty(userIds)) {
                    return;
                }
                if (!org.springframework.util.StringUtils.isEmpty(req.getUserId())) {
                    if (!userIds.contains(req.getUserId())) {
                        return;
                    }
                    userIds = new ArrayList<>();
                    userIds.add(req.getUserId());
                }
                feignReq.setUserIds(userIds);
            }
            // 有转出用户钱包ID，无转出用户昵称
            if (org.springframework.util.CollectionUtils.isEmpty(feignReq.getUserIds()) && !org.springframework.util.StringUtils.isEmpty(req.getUserId())) {
                List<String> userIds = new ArrayList<>();
                userIds.add(req.getUserId());
                feignReq.setUserIds(userIds);
            }

            BeanUtils.copyProperties(req, feignReq);

            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignReq.setSize(maxPageSize==null?EX_PAGE_SIZE:Integer.valueOf(maxPageSize));

            BaseResult result = transferWalletFeign.salaryInPageList(feignReq);
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"), SalaryInResp.class);
                if (!org.springframework.util.CollectionUtils.isEmpty(respList)) {
                    // 设置数据内部属性
                    resetRespList(respList);
                    //设置属性值
                    resetStatusValue(respList);
                }
            }
            if(respList == null) {
                respList = new ArrayList<>();
            }
            String downloadFileName = getDownloadFileName(req.getCreateTime(), null);
            // 导出文件
            ExcelUtil.exportExcel(response, respList, downloadFileName,SALARY_IN_FILENAME);
        }catch(Exception e){
            log.error("导出失败",e);
            return;
        }finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public void salaryOutDownloadList(SalaryInPageListReq req, HttpSession session, HttpServletResponse response) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_SALARY_IN_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            List<SalaryInResp> respList = null;
            FeignSalaryInPageListReq feignReq = new FeignSalaryInPageListReq();
            if (!org.springframework.util.StringUtils.isEmpty(req.getUserName())) {
                List<String> userIds = getUserIds(req.getUserName());
                if (org.springframework.util.CollectionUtils.isEmpty(userIds)) {
                    return;
                }
                if (!org.springframework.util.StringUtils.isEmpty(req.getUserId())) {
                    if (!userIds.contains(req.getUserId())) {
                        return;
                    }
                    userIds = new ArrayList<>();
                    userIds.add(req.getUserId());
                }
                feignReq.setUserIds(userIds);
            }
            // 有转出用户钱包ID，无转出用户昵称
            if (org.springframework.util.CollectionUtils.isEmpty(feignReq.getUserIds()) && !org.springframework.util.StringUtils.isEmpty(req.getUserId())) {
                List<String> userIds = new ArrayList<>();
                userIds.add(req.getUserId());
                feignReq.setUserIds(userIds);
            }

            BeanUtils.copyProperties(req, feignReq);

            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignReq.setSize(maxPageSize==null?EX_PAGE_SIZE:Integer.valueOf(maxPageSize));

            BaseResult result = transferWalletFeign.salaryOutPageList(feignReq);
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"), SalaryInResp.class);
                if (!org.springframework.util.CollectionUtils.isEmpty(respList)) {
                    // 设置数据内部属性
                    resetRespList(respList);
                    //设置属性值
                    resetStatusValue(respList);
                }
            }
            if(respList == null) {
                respList = new ArrayList<>();
            }
            String downloadFileName = getDownloadFileName(req.getCreateTime(), null);
            // 导出文件
            ExcelUtil.exportExcel(response, respList, downloadFileName,SALARY_IN_FILENAME);
        }catch(Exception e){
            log.error("导出失败",e);
            return;
        }finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    /**
     *
     * @Description 命名文件名
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getDownloadFileName(Date startTime, Date endTime) {
        String fileName = SALARY_IN_FILENAME;
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

    /**
     *
     * @Description 获取用户昵称换取钱包ID
     * @param userName
     * @return
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
     * @Description 设置数据内部属性
     * @param respList
     */
    private void resetRespList(List<SalaryInResp> respList) {
        HashSet<String> userIds = new HashSet<>();
        // 获取钱包ID去重集合
        respList.stream().forEach(resp -> {
            if(!StringUtils.isEmpty(resp.getUserId())) {
                userIds.add(resp.getUserId());
            }
        });
        // 根据钱包ID集合获取用户昵称集合
        Map<String, String> userNameMap = getUserNameMap(userIds);
        // 设置用户昵称
        respList.stream().forEach(resp -> {
            resp.setUserName(userNameMap.get(resp.getUserId()));
        });
    }

    /**
     * @Description 设置状态值
     * @param respList
     */
    private void resetStatusValue(List<SalaryInResp> respList) {
        respList.stream().forEach(resp -> {
            if(resp.getStatus()!=null) {
                resp.setStatusValue(STATUSVALUES[resp.getStatus()]);
            };
        });
    }

    /**
     *
     * @Description 获取币种名称集合
     * @return
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

    /**
     *
     * @Description 获取用户名集合
     * @param userIds
     * @return
     */
    private Map<String, String> getUserNameMap(HashSet<String> userIds) {
        Map<String, String> userNameMap = new HashMap<>();
        //根据钱包ID兑换accountId
        HashSet<String> accountIds =  new HashSet<>();
        Map<String, String> accountIdsMap = new HashMap<>();
        FeignTransferUserReq req = new FeignTransferUserReq();
        req.setUserIdList(new ArrayList<>(userIds));
        BaseResult coreResult = transferWalletFeign.listTransferUserNames(req);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
            List<WalletUserResp> walletUserResps = JSONObject.parseArray(jsonObject.getString("list"), WalletUserResp.class);
            if (!CollectionUtils.isEmpty(walletUserResps)) {
                walletUserResps.stream().forEach(record -> {
                    accountIdsMap.put(record.getId(),record.getPlatformUserId());
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
                        userIds.stream().forEach(userId->{
                            userNameMap.put(userId, userIdsMap.get(accountIdsMap.get(userId)));
                        });
                    }
                }
            }
        }
        return userNameMap;
    }

}
