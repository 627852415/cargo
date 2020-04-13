package com.lxtx.im.admin.service.impl.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TransferFriendsFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.TransferFriendsDetailReq;
import com.lxtx.im.admin.service.request.TransferFriendsReq;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.transaction.AsyncTransferFriendsService;
import com.lxtx.im.admin.service.transaction.TransferFriendsService;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author qing
 * @Description 好友转账
 * @date: 2019年11月20日 下午6:23:27
 */
@Slf4j
@Service
public class TransferFriendsServiceImpl implements TransferFriendsService {

    // 好友转账
    private static final String APPLY_LIST_FILENAME = "好友转账列表";

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    // 表状态：status=（【1：待处理，2：处理中，3：已提交，4：成功，5:失败】）
    // 页面展示状态：status=（1：处理中（1,2,3），2：成功（4），3：失败（5））
    private static final String[] STATUSVALUES = {"处理中", "处理中", "处理中", "成功", "失败"};

    // 导出数据要加数据数量限制，数量值字典全局配置（暂定50000）条
    // 导出数据条限制
    private static Integer EX_PAGE_SIZE = 50000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;

    //临时目录
    private static String SEGMENTEDFILEPRE = "/tmp";

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
    private TransferFriendsFeign transferFriendsFeign;

    @Autowired
    private DictService dictService;


    @Autowired
    private AsyncTransferFriendsService asyncTransferFriendsService;

    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(TransferFriendsReq req, HttpSession session) {

        //发送人的userIdList
        List<String> sendUserIdList = null;

        //接收人的userIdList
        List<String> receiverIdList = null;

        //发送人根据用户昵称、国家简码、手机号查询 (注意：若输入sendUserId，求交集)
        if (StringUtils.isNotBlank(req.getUserName()) || StringUtils.isNotBlank(req.getCountryCodeOut())
                || StringUtils.isNotBlank(req.getTelephoneOut()) || StringUtils.isNotBlank(req.getUserId())) {
            FeignQueryUserIdByImUserReq queryUserIdByImUserReq = new FeignQueryUserIdByImUserReq();
            if (StringUtils.isNotBlank(req.getUserName())) {
                queryUserIdByImUserReq.setUserName(req.getUserName());
            }
            if (StringUtils.isNotBlank(req.getCountryCodeOut())) {
                queryUserIdByImUserReq.setCountryCode(req.getCountryCodeOut());
            }
            if (StringUtils.isNotBlank(req.getTelephoneOut())) {
                queryUserIdByImUserReq.setTelephone(req.getTelephoneOut());
            }
            if (StringUtils.isNotBlank(req.getUserId())) {
                queryUserIdByImUserReq.setUserId(req.getUserId());
            }
            BaseResult queryUserIdByImUserResult = walletUserFeign.queryUserIdByImUser(queryUserIdByImUserReq);
            if (queryUserIdByImUserResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryUserIdByImUserResult.getData()));
                sendUserIdList = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(sendUserIdList)) {
                    return BaseResult.success(new Page<>());
                }
            } else {
                return BaseResult.success(new Page<>());
            }
        }
        //接收人根据用户昵称、国家简码、手机号查询(注意：若输入receiverId，求交集)
        if (StringUtils.isNotBlank(req.getReceiverUserName()) || StringUtils.isNotBlank(req.getCountryCodeIn())
                || StringUtils.isNotBlank(req.getTelephoneIn()) || StringUtils.isNotBlank(req.getReceiverId())) {
            FeignQueryUserIdByImUserReq queryUserIdByImUserReq = new FeignQueryUserIdByImUserReq();
            if (StringUtils.isNotBlank(req.getUserName())) {
                queryUserIdByImUserReq.setUserName(req.getReceiverUserName());
            }
            if (StringUtils.isNotBlank(req.getCountryCodeIn())) {
                queryUserIdByImUserReq.setCountryCode(req.getCountryCodeIn());
            }
            if (StringUtils.isNotBlank(req.getTelephoneOut())) {
                queryUserIdByImUserReq.setTelephone(req.getTelephoneIn());
            }
            if (StringUtils.isNotBlank(req.getReceiverId())) {
                queryUserIdByImUserReq.setUserId(req.getReceiverId());
            }
            BaseResult queryUserIdByImUserResult = walletUserFeign.queryUserIdByImUser(queryUserIdByImUserReq);
            if (queryUserIdByImUserResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryUserIdByImUserResult.getData()));
                receiverIdList = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(receiverIdList)) {
                    return BaseResult.success(new Page<>());
                }
            } else {
                return BaseResult.success(new Page<>());
            }
        }

        FeignTransferFriendsReq feignReq = new FeignTransferFriendsReq();
        BeanUtils.copyProperties(req, feignReq);
        boolean isKHShowAccount = userService.isShowAccount();
        feignReq.setKHShowAccount(isKHShowAccount);
        if (isKHShowAccount) {
            BaseResult baseResult = userFeign.selectKhUser();
            if (baseResult.isSuccess() && null != baseResult.getData()) {
                List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                feignReq.setKhUserAccountList(userAccountList);
            }
        }

        if (!CollectionUtils.isEmpty(sendUserIdList)) {
            feignReq.setUserIds(sendUserIdList);
        }
        if (!CollectionUtils.isEmpty(receiverIdList)) {
            feignReq.setReceiverIds(receiverIdList);
        }
        BaseResult result = transferFriendsFeign.listPage(feignReq);
        return result;
    }

    @Override
    public BaseResult downloadLock(TransferFriendsReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_FRIENDS_LOCK.concat(userId);
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
    public void downloadList(TransferFriendsReq req, HttpSession session, HttpServletResponse response) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_FRIENDS_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            req.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));
            long start = System.currentTimeMillis();

            BaseResult result = listPage(req, session);
            List<TransferFriendsResp> respList = Lists.newArrayList();
            log.info("TransferFriendsFeign.list查询耗时：{}ms", (System.currentTimeMillis() - start));
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                respList = JSONObject.parseArray(jsonObject.getString(BaseResult.RECORDS), TransferFriendsResp.class);
                if (!CollectionUtils.isEmpty(respList)) {
                    // 设置数据内部属性
                    resetRespList(respList);
                }
            }
            long startEx = System.currentTimeMillis();
            // 导出文件
            ExcelUtil.exportExcel(response, respList,
                    getPlatformWithdrawApplyDownloadFileName(req.getCreateTimeStart(), req.getCreateTimeEnd()),
                    APPLY_LIST_FILENAME);
            log.info("ExportExcel产生耗时：{}ms", (System.currentTimeMillis() - startEx));
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public void downloadListAll(TransferFriendsReq req, HttpSession session, HttpServletResponse response) {
        String pageSizeStr = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
        Integer pageSize = pageSizeStr == null ? 30000 : Integer.valueOf(pageSizeStr);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        List<File> files = new ArrayList<>();
        FeignTransferFriendsReq feignReq = new FeignTransferFriendsReq();
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
        // 接收用户昵称换取用户钱包ID 且与 接收用户钱包ID进行条件过滤
        if (!StringUtils.isEmpty(req.getReceiverUserName())) {
            List<String> receiverIds = getUserIds(req.getReceiverUserName());
            if (CollectionUtils.isEmpty(receiverIds)) {
                return;
            }
            if (!StringUtils.isEmpty(req.getReceiverId())) {
                if (!receiverIds.contains(req.getReceiverId())) {
                    return;
                }
                receiverIds = new ArrayList<>();
                receiverIds.add(req.getReceiverId());
            }
            feignReq.setReceiverIds(receiverIds);
        }
        // 有接收用户钱包ID，无接收用户昵称
        if (CollectionUtils.isEmpty(feignReq.getReceiverIds()) && !StringUtils.isEmpty(req.getReceiverId())) {
            List<String> receiverIds = new ArrayList<>();
            receiverIds.add(req.getReceiverId());
            feignReq.setReceiverIds(receiverIds);
        }
        BeanUtils.copyProperties(req, feignReq);

        if (userService.isShowAccount()) {
            BaseResult baseResult = userFeign.selectKhUser();
            if (baseResult.isSuccess() && null != baseResult.getData()) {
                List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                feignReq.setKhUserAccountList(userAccountList);
            }
        }

        BaseResult result = transferFriendsFeign.listCount(feignReq);
        if (result.isSuccess() && result.getData() != null) {
            //先获取条数计算分页
            Integer sumCount = Integer.valueOf(result.getData().toString());
            int count = (int) Math.ceil((double) sumCount / pageSize);
            try {
                boolean async = true;//异步
                if (async) {
                    //控制并发
                    int tcount = 5;
                    int fcount = 0;
//					CountDownLatch cdl = new CountDownLatch(count);
                    CountDownLatch cdlfor = null; //new CountDownLatch(count);
                    for (int i = 1; i <= count; i++) {
                        //异步处理
                        if (cdlfor == null) {
                            fcount = (count - (i - 1) >= tcount) ? tcount : (count - (i - 1));
                            cdlfor = new CountDownLatch(fcount);
                        }
                        TransferFriendsReq reqTemp = new TransferFriendsReq();
                        BeanUtils.copyProperties(req, reqTemp);
                        reqTemp.setCurrent(i);
                        reqTemp.setSize(pageSize);
                        File segmentedFileTmp = new File(SEGMENTEDFILEPRE + File.separatorChar + uuid + File.separatorChar + i);
                        files.add(segmentedFileTmp);
                        asyncTransferFriendsService.asyncListPage(reqTemp, segmentedFileTmp, cdlfor, session);
                        if (fcount > 0) fcount--;
                        //分段等待分段数据全部返回
                        if (cdlfor != null && fcount == 0) {
                            cdlfor.await();
                            cdlfor = null;
                        }
                    }
//					//等待分段数据全部返回
//					cdl.await();
                } else {
                    for (int i = 1; i <= count; i++) {
                        //再异步处理第一页往后的
                        TransferFriendsReq reqTemp = new TransferFriendsReq();
                        BeanUtils.copyProperties(req, reqTemp);
                        reqTemp.setCurrent(i);
                        reqTemp.setSize(pageSize);
                        File segmentedFileTmp = new File(SEGMENTEDFILEPRE + File.separatorChar + uuid + File.separatorChar + i);
                        files.add(segmentedFileTmp);
                        syncListPage(reqTemp, segmentedFileTmp, session);
                    }
                }
                File segmentedFileExcel = new File(SEGMENTEDFILEPRE + File.separatorChar + uuid + File.separatorChar + "segmented_file_excel.xls");
                ExcelUtil.segmentedsAddToExcel(TransferFriendsResp.class, segmentedFileExcel, files, APPLY_LIST_FILENAME);
                String fileName = getPlatformWithdrawApplyDownloadFileName(req.getCreateTimeStart(), req.getCreateTimeEnd());
                // 设置response参数，可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xls");
                try (
                        ServletOutputStream out = response.getOutputStream();
                        FileInputStream fis = new FileInputStream(segmentedFileExcel);
                        BufferedOutputStream bos = new BufferedOutputStream(out);
                ) {
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = fis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ExcelUtil.segmentedsFiles(new File(SEGMENTEDFILEPRE + File.separatorChar + uuid));
            }

        }
    }

    @Override
    public String detail(TransferFriendsDetailReq req, Model model) {
        TransferFriendsResp resp = null;
        FeignTransferFriendsReq feignReq = new FeignTransferFriendsReq();
        feignReq.setId(req.getId());
        BaseResult result = transferFriendsFeign.listPage(feignReq);
        if (result.isSuccess() && result.getData() != null) {
            List<TransferFriendsResp> respList = null;
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            respList = JSONObject.parseArray(jsonObject.getString("records"), TransferFriendsResp.class);
            if ((!CollectionUtils.isEmpty(respList)) && respList.size() > 0) {
                // 设置数据内部属性
                resetRespList(respList);
                resp = respList.get(0);
            }
        }
        model.addAttribute("detail", resp);
        return "transaction/transfer-friends-detail";
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
        BaseResult coreResult = transferFriendsFeign.listTransferUserNames(req);
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

    private Map<String, String> getAliasCoinNameMap(boolean isKHAccount) {
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
    private void resetRespList(List<TransferFriendsResp> respList) {
        HashSet<String> userIds = new HashSet<>();
        // 获取钱包ID去重集合
        respList.stream().forEach(resp -> {
            if (!StringUtils.isEmpty(resp.getUserId())) {
                userIds.add(resp.getUserId());
            }
            if (!StringUtils.isEmpty(resp.getReceiverId())) {
                userIds.add(resp.getReceiverId());
            }
        });
        // 根据钱包ID集合获取用户昵称集合
        Map<String, String> userNameMap = getUserNameMap(userIds);
        // 获取币种名称集合
        Map<String, String> coinNameMap = getAliasCoinNameMap(userService.isShowAccount());
        // 设置转出用户昵称、接收用户昵称
        respList.stream().forEach(resp -> {
            resp.setUserName(userNameMap.get(resp.getUserId()));
            resp.setReceiverUserName(userNameMap.get(resp.getReceiverId()));
            resp.setCoinName(coinNameMap.get(resp.getCoinId()));
            resp.setStatusVaule(STATUSVALUES[resp.getStatus() - 1]);
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

    private boolean syncListPage(TransferFriendsReq req, File segmentedFile, HttpSession session) {
        try {
            FeignTransferFriendsReq feignReq = new FeignTransferFriendsReq();
            // 转出用户昵称换取用户钱包ID 且与 转出用户钱包ID进行条件过滤
            if (!StringUtils.isEmpty(req.getUserName())) {
                List<String> userIds = getUserIds(req.getUserName());
                if (CollectionUtils.isEmpty(userIds)) {
                    return true;
                }
                if (!StringUtils.isEmpty(req.getUserId())) {
                    if (!userIds.contains(req.getUserId())) {
                        return true;
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
            // 接收用户昵称换取用户钱包ID 且与 接收用户钱包ID进行条件过滤
            if (!StringUtils.isEmpty(req.getReceiverUserName())) {
                List<String> receiverIds = getUserIds(req.getReceiverUserName());
                if (CollectionUtils.isEmpty(receiverIds)) {
                    return true;
                }
                if (!StringUtils.isEmpty(req.getReceiverId())) {
                    if (!receiverIds.contains(req.getReceiverId())) {
                        return true;
                    }
                    receiverIds = new ArrayList<>();
                    receiverIds.add(req.getReceiverId());
                }
                feignReq.setReceiverIds(receiverIds);
            }
            // 有接收用户钱包ID，无接收用户昵称
            if (CollectionUtils.isEmpty(feignReq.getReceiverIds()) && !StringUtils.isEmpty(req.getReceiverId())) {
                List<String> receiverIds = new ArrayList<>();
                receiverIds.add(req.getReceiverId());
                feignReq.setReceiverIds(receiverIds);
            }
            BeanUtils.copyProperties(req, feignReq);
            BaseResult result = transferFriendsFeign.list(feignReq);
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                List<TransferFriendsResp> respList = JSONObject.parseArray(jsonObject.getString("list"), TransferFriendsResp.class);
                if (!CollectionUtils.isEmpty(respList)) {
                    // 设置数据内部属性
                    resetRespList(respList);
                    // 存储临时分片
                    ExcelUtil.segmentedDatas(respList, segmentedFile);
                }
            }
            return true;
        } catch (Exception e) {
            log.error("获取异步分片异常", e);
            return false;
        }
    }
}
