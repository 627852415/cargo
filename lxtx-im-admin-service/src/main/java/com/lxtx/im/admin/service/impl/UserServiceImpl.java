package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.ArrayUtil;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.MessageUtil;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.framework.common.utils.message.FeignMessageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.Constants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumUserState;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.UserList;
import com.lxtx.im.admin.service.response.UserResp;
import com.lxtx.im.admin.service.response.UserResp2;
import com.lxtx.im.admin.service.response.WalletUserResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeign userFeign;
    @Resource
    private DictService dictService;

    @Resource
    private WalletUserFeign walletUserFeign;

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;

    // 导出数据条限制
    private static Integer EX_PAGE_SIZE = 1000000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;

    // 钱包转账
    private static final String APPLY_LIST_FILENAME = "IM用户列表";

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    @Override
    public BaseResult listPage(UserListPageReq req) {
        FeignUserListReq userListReq = new FeignUserListReq();
        BeanUtils.copyProperties(req, userListReq);

        String domain = DictConstants.DICT_DOMAIN_OFFSITE_EXCHANGE;
        String key = DictConstants.DICT_KEY_OFFSITE_CAPITAL_USERID;

        //查询换汇资金账号，用于IM用户管理不显示换汇资金账号，若字典查询不到，则显示全部
        String userId = dictService.getDictValue(domain, key);
        if (StringUtils.isNotBlank(userId)) {
            FeignWalletUserInfoReq walletUserInfoReq = new FeignWalletUserInfoReq();
            walletUserInfoReq.setUserId(userId);
            BaseResult baseResult = walletUserFeign.queryWalletUserById(walletUserInfoReq);
            if (baseResult.isSuccess()) {
                String walletUserJson = JSONObject.toJSONString(baseResult.getData());
                WalletUserResp walletUserResp = JSONObject.parseObject(walletUserJson, WalletUserResp.class);
                String platformUserId = walletUserResp.getPlatformUserId();
                FeignUserDetailReq detailReq = new FeignUserDetailReq();
                detailReq.setAccount(platformUserId);
                BaseResult detail = userFeign.detail(detailReq);
                if (detail.isSuccess()) {
                    userListReq.setExcludeAccount(platformUserId);
                    log.info("************ IM用户管理-换汇资金账号存在 ************");
                    return userFeign.listPage(userListReq);
                }
            }
        }
        log.info("************ IM用户管理-换汇资金账号不存在 ************");
        return userFeign.listPage(userListReq);
    }

    @Override
    public BaseResult downloadLock(UserListPageReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_USER_LOCK.concat(userId);
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
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(com.lxtx.im.admin.service.Constants.DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, com.lxtx.im.admin.service.Constants.DictConstants.TRANSACTION_EXPORT_SIZE);
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
    public void downloadList(UserListPageReq req, HttpSession session, HttpServletResponse response) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_USER_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            // 设置导出页数
            String maxPageSize = dictService.getDictValue(com.lxtx.im.admin.service.Constants.DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, com.lxtx.im.admin.service.Constants.DictConstants.TRANSACTION_EXPORT_SIZE);
            req.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            BaseResult result = listPage(req);
            List<UserResp2> userResp2s = null;
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONStringWithDateFormat(result.getData(), "yyyy-MM-dd HH:mm:ss"));
                userResp2s = JSONObject.parseArray(jsonObject.getString(BaseResult.RECORDS), UserResp2.class);
            }

            //格式化成普通日期格式
            Function<String, String> strToDateStr = dateStr -> {
//            String dateStr = "Jun 26,2014 4:15:04 PM";
                DateFormat formatFrom = new SimpleDateFormat("MMM dd,yyyy KK:mm:ss aa", Locale.ENGLISH);
                Date date = null;
                try {
                    date = formatFrom.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return formatTo.format(date);
            };

            if (CollectionUtils.isNotEmpty(userResp2s)) {
                userResp2s.parallelStream()
                        .forEach(userResp2 -> {
                            String loginTime = userResp2.getLoginTime();
                            String createTime = userResp2.getCreateTime();
                            if (StringUtils.isNotBlank(loginTime)) {
                                String loginT = strToDateStr.apply(loginTime);
                                userResp2.setLoginTime(loginT);
                            }
                            if (StringUtils.isNotBlank(createTime)) {
                                String createT = strToDateStr.apply(createTime);
                                userResp2.setCreateTime(createT);
                            }
                        });
            }else {
                userResp2s = new ArrayList<>();
            }
            // 导出文件
            ExcelUtil.exportExcel(response, userResp2s,
                    APPLY_LIST_FILENAME,
                    APPLY_LIST_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public BaseResult operateState(UserStateOperateReq req) {
        FeignUserStateOperateReq userStateOperateReq = new FeignUserStateOperateReq();
        BeanUtils.copyProperties(req, userStateOperateReq);
        BaseResult baseResult = userFeign.operateState(userStateOperateReq);
        if (baseResult.isSuccess() && EnumUserState.FORBIDDEN.getCode().equals(req.getState())) {
            // 发送下线消息
            FeignMessageReq feignMessageReq = new FeignMessageReq();
            feignMessageReq.setAccount(req.getAccount());
            feignMessageReq.setAction(com.lxtx.framework.common.constants.Constants.MessageAction.ACTION_999);
            feignMessageReq.setReceiver(req.getAccount());
            feignMessageReq.setSender(com.lxtx.framework.common.constants.Constants.SYSTEM);
            feignMessageReq.setContent(new JSONObject().toJSONString());
            MessageUtil.sendAsyncSingleCustomMsg(FeignSendMsgReq.getSingleSendMsgReq(feignMessageReq));
//            if(!send.isSuccess()){
//                throw LxtxBizException.newException("发送下线消息失败");
//            }
        }
        return baseResult;
    }

    /**
     * 重置密码
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult resetPsd(UserResetPsdReq req) {
        FeignUserResetPsdReq userStateOperateReq = new FeignUserResetPsdReq();
        userStateOperateReq.setAccount(req.getAccount());
        userStateOperateReq.setPassword(DigestUtils.md5DigestAsHex(Constants.DEFAULT_PSD.getBytes()).toUpperCase());
        return userFeign.resetPsd(userStateOperateReq);
    }

    /**
     * 修改账号信息
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult modify(UserModifyReq req) {
        FeignUserModifyReq userModifyReq = new FeignUserModifyReq();
        BeanUtils.copyProperties(req, userModifyReq);
        return userFeign.modify(userModifyReq);
    }

    /**
     * 获取最新的各个国家手机区号列表
     *
     * @return
     */
    @Override
    public BaseResult getGlobalCodeList() {
        FeignGetGlobalCodeListReq req = new FeignGetGlobalCodeListReq();
        req.setVersion(0);
        return userFeign.getGlobalCodeList(req);
    }

    @Override
    public BaseResult synchronizeYunxinUser(UserYXSyncReq userReq) {

        log.info("************IM用户同步网易云信开始************");

        String[] accounts = userReq.getAccount().split(",");

        List<String> lis = Arrays.asList(accounts);

        FeignQueryUserListByIdReq req = new FeignQueryUserListByIdReq();
        req.setIds(lis);
        BaseResult result = userFeign.selectBatchIds(req);
        UserList userListResp = JSONObject.parseObject(JSONArray.toJSONString(result.getData()), UserList.class);

        List<UserResp> list = userListResp.getList();

        List<String> existUser = new ArrayList<>();
        for (UserResp user : list) {
            if (0 == user.getState()) {
                existUser.add(user.getAccount());
            }
        }

        List minusString = ArrayUtil.minusString(lis.toArray(new String[lis.size()]), existUser.toArray(new String[existUser.size()]));

        if (CollectionUtils.isNotEmpty(minusString)) {
            return BaseResult.error(null, "帐号：" + minusString.toString() + "不存在,请检查好数据再同步");
        }


//        for (UserResp user : list) {
//            FeignYunXinCreateReq createReq = new FeignYunXinCreateReq();
//            createReq.setAccount(user.getAccount());
//            BaseResult yxResult = yunxinFeign.getUser(createReq);
//            if (yxResult.isSuccess()) {
//
//            }
//
//        }

        // 查询IM激活的用户
//        BaseResult coreResult = userFeign.queryActive();
//
//        if (coreResult.isSuccess() && coreResult.getData() != null) {
//            UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()), UserListResp.class);
//            List<UserDetailResp> userDetailResps = userListResp.getList();
//
//            if (CollectionUtils.isEmpty(userDetailResps)) {
//                log.info("************ IM用户为空 ************ ");
//                return BaseResult.success();
//            }
//            for (UserDetailResp resp : userDetailResps) {
//                FeignYunXinCreateReq req = new FeignYunXinCreateReq();
//                String account = resp.getAccount();
//                req.setAccount(account);
//
//                try {
//                    BaseResult result = userFeign.synchronizeYunxinUser(req);
//                    if (result.isSuccess()) {
//                        log.info("************ 帐号：{} 同步成功 ************", account);
//                    } else {
//                        log.info("************ 帐号：{} 同步失败 , 原因: {} ************", account, result.getMsg());
//                    }
//                } catch (Exception e) {
//                    log.error("************ 帐号：{} 同步异常 , 异常原因: {} ************", account, e);
//                }
//            }
//        }
//        log.info("************ IM用户同步网易云信结束 ************");
        return BaseResult.success();
    }

    @Override
    public List<String> getUserAccountByUserInfo(UserInfoReq req){
        FeignUserInfoReq feignReq = new FeignUserInfoReq();
        BeanUtils.copyProperties(req,feignReq);
        BaseResult baseResult = userFeign.getUserAccountByUserInfo(feignReq);
        List<String> accountList = null;
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            accountList = JSONObject.parseArray(jsonObject.getString("list"), String.class);
        }
        return accountList;
    }

    @Override
    public BaseResult initAgoraUid() {
        return userFeign.initAgoraUid();
    }

    @Override
    public BaseResult initUserUid() {
        return userFeign.initUserUid();
    }

    @Override
    public boolean isShowAccount() {
        String userName = ShiroUtils.getUserName();
        String dictValue = dictService.getDictValue(DictConstants.DICT_DOMAIN_SYSTEM_MANAGER, DictConstants.DICT_DOMAIN_SPECIAL_HK_ACCOUNT);
        if(StringUtils.isNotBlank(dictValue)){
            //判断是否柬埔寨演示账号
            String[] names = dictValue.split(",");
            List<String> list = Arrays.asList(names);
            if(CollectionUtils.isNotEmpty(list)){
                if(list.contains(userName)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public BaseResult kickUserOffline(UserReq req) {
        // 发送下线消息
        FeignMessageReq feignMessageReq = new FeignMessageReq();
        feignMessageReq.setAccount(req.getAccount());
        feignMessageReq.setAction(com.lxtx.framework.common.constants.Constants.MessageAction.ACTION_999);
        feignMessageReq.setReceiver(req.getAccount());
        feignMessageReq.setSender(com.lxtx.framework.common.constants.Constants.SYSTEM);
        feignMessageReq.setContent(new JSONObject().toJSONString());

        BaseResult baseResult = null;
        try {
            baseResult = MessageUtil.sendSingleCustomMsg(FeignSendMsgReq.getSingleSendMsgReq(feignMessageReq));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw LxtxBizException.newException("发送下线消息失败");
        }
        if(!baseResult.isSuccess()){
            throw LxtxBizException.newException("发送下线消息失败");
        }
        // 更新数据库状态
        FeignKickUserOfflineReq userOfflineReq = new FeignKickUserOfflineReq();
        userOfflineReq.setAccount(req.getAccount());
        userFeign.kickOffline(userOfflineReq);
        return BaseResult.success("Successful operation");
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
