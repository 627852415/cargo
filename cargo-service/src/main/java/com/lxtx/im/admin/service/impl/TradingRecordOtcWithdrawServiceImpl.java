package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TradingRecordOtcWithdrawFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.FeignQueryUserIdByImUserReq;
import com.lxtx.im.admin.feign.request.FeignQueryWalletUserByAddrReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordOtcWithdrawDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordOtcWithdrawReq;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.TradingRecordOtcWithdrawService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.TradingRecordOtcWithdrawDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordOtcWithdrawReq;
import com.lxtx.im.admin.service.response.TradingRecordOtcWithdrawResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TradingRecordOtcWithdrawServiceImpl implements TradingRecordOtcWithdrawService {
    private static final String APPLY_LIST_FILENAME = "OTC提现交易流水列表";

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private TradingRecordOtcWithdrawFeign tradingRecordOtcWithdrawFeign;

    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private WalletUserCoinFeign walletUserCoinFeign;

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;

    private static Integer EX_PAGE_SIZE = 50000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;


    @Override
    public BaseResult listPage(TradingRecordOtcWithdrawReq req, HttpSession session) {
        List<String> userIdListByImInfo = null;
        List<String> userIdListByAddress = null;
        //根据用户昵称、国家简码、手机号查询
        if (StringUtils.isNotBlank(req.getUserName()) || StringUtils.isNotBlank(req.getCountryCode()) || StringUtils.isNotBlank(req.getTelephone())) {
            FeignQueryUserIdByImUserReq queryUserIdByImUserReq = new FeignQueryUserIdByImUserReq();
            if (StringUtils.isNotBlank(req.getUserName())) {
                queryUserIdByImUserReq.setUserName(req.getUserName());
            }
            if (StringUtils.isNotBlank(req.getCountryCode())) {
                queryUserIdByImUserReq.setCountryCode(req.getCountryCode());
            }
            if (StringUtils.isNotBlank(req.getTelephone())) {
                queryUserIdByImUserReq.setTelephone(req.getTelephone());
            }
            BaseResult queryUserIdByImUserResult = walletUserFeign.queryUserIdByImUser(queryUserIdByImUserReq);
            if (queryUserIdByImUserResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryUserIdByImUserResult.getData()));
                userIdListByImInfo = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(userIdListByImInfo)) {
                    return BaseResult.success(new Page<>());
                }
            } else {
                return BaseResult.success(new Page<>());
            }
        }
        //如果用户既输入了钱包ID，转账地址、用户昵称，则要根据这三个条件查出的walletUserID做一个刷选，取交集
        if (StringUtils.isNotBlank(req.getAssignAddr())) {
            FeignQueryWalletUserByAddrReq queryWalletUserByAddrReq = new FeignQueryWalletUserByAddrReq();
            queryWalletUserByAddrReq.setAssignAddr(req.getAssignAddr());
            BaseResult queryWalletUser = walletUserCoinFeign.queryWalletUserByAddr(queryWalletUserByAddrReq);
            if (queryWalletUser.isSuccess() && queryWalletUser.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryWalletUser.getData()));
                userIdListByAddress = JSONObject.parseArray(JSON.toJSONString(jsonObject.getJSONObject("data").getJSONArray("list")), String.class);
                if (CollectionUtils.isEmpty(userIdListByAddress)) {
                    return BaseResult.success(new Page<>());
                }
            } else {
                return BaseResult.success(new Page<>());
            }
        }

        FeignTradingRecordOtcWithdrawReq feignTradingRecordOtcReq = new FeignTradingRecordOtcWithdrawReq();
        BeanUtils.copyProperties(req, feignTradingRecordOtcReq);
        if (!CollectionUtils.isEmpty(userIdListByAddress)) {
            feignTradingRecordOtcReq.setUserIdsByAddr(userIdListByAddress);
        }
        if (!CollectionUtils.isEmpty(userIdListByImInfo)) {
            feignTradingRecordOtcReq.setUserIdsByUserName(userIdListByImInfo);
        }
            if (userService.isShowAccount()) {
                BaseResult baseResult = userFeign.selectKhUser();
                if (baseResult.isSuccess() && null != baseResult.getData()) {
                    List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                    feignTradingRecordOtcReq.setKhUserAccountList(userAccountList);
                }
            }

        BaseResult baseResult = tradingRecordOtcWithdrawFeign.listPage(feignTradingRecordOtcReq);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<TradingRecordOtcWithdrawResp> respList = JSONObject.parseArray(jsonObject.getString("records"),
                    TradingRecordOtcWithdrawResp.class);
            respList.stream().forEach(resp -> {
                setState1Value(resp);
            });
            jsonObject.put("records", respList);
            baseResult.setData(jsonObject);
        }
        return baseResult;
    }

    @Override
    public BaseResult downloadLock(TradingRecordOtcWithdrawReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_OTC_WITHDRAW_LOCK.concat(userId);
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
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordOtcWithdrawReq req) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_OTC_WITHDRAW_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            List<TradingRecordOtcWithdrawResp> respList = null;
            List<String> userIds = null;
            //根据用户昵称、国家简码、手机号查询
            if (StringUtils.isNotBlank(req.getUserName()) || StringUtils.isNotBlank(req.getCountryCode()) || StringUtils.isNotBlank(req.getTelephone())) {
                FeignQueryUserIdByImUserReq queryUserIdByImUserReq = new FeignQueryUserIdByImUserReq();
                if (StringUtils.isNotBlank(req.getUserName())) {
                    queryUserIdByImUserReq.setUserName(req.getUserName());
                }
                if (StringUtils.isNotBlank(req.getCountryCode())) {
                    queryUserIdByImUserReq.setCountryCode(req.getCountryCode());
                }
                if (StringUtils.isNotBlank(req.getTelephone())) {
                    queryUserIdByImUserReq.setTelephone(req.getTelephone());
                }
                BaseResult queryUserIdByImUserResult = walletUserFeign.queryUserIdByImUser(queryUserIdByImUserReq);
                if (queryUserIdByImUserResult.isSuccessAndDataNotNull()) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryUserIdByImUserResult.getData()));
                    userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                    if (CollectionUtils.isEmpty(userIds)) {
                        respList = Lists.newArrayList();
                    }
                } else {
                    respList = Lists.newArrayList();
                }
            }
            //如果用户既输入了钱包ID，转账地址、用户昵称，则要根据这三个条件查出的walletUserID做一个刷选，取交集
            if (StringUtils.isNotBlank(req.getAssignAddr())) {
                FeignQueryWalletUserByAddrReq queryWalletUserByAddrReq = new FeignQueryWalletUserByAddrReq();
                queryWalletUserByAddrReq.setAssignAddr(req.getAssignAddr());
                BaseResult queryWalletUser = walletUserCoinFeign.queryWalletUserByAddr(queryWalletUserByAddrReq);
                if (queryWalletUser.isSuccess() && queryWalletUser.getData() != null) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryWalletUser.getData()));
                    userIds = JSONObject.parseArray(JSON.toJSONString(jsonObject.getJSONObject("data").getJSONArray("list")), String.class);
                    if (CollectionUtils.isEmpty(userIds)) {
                        respList = Lists.newArrayList();
                    }
                } else {
                    respList = Lists.newArrayList();
                }
            }

            FeignTradingRecordOtcWithdrawReq feignTradingRecordOtcReq = new FeignTradingRecordOtcWithdrawReq();
            BeanUtils.copyProperties(req, feignTradingRecordOtcReq);
            feignTradingRecordOtcReq.setUserIds(userIds);
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignTradingRecordOtcReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            if (userService.isShowAccount()) {
                BaseResult baseResult = userFeign.selectKhUser();
                if (baseResult.isSuccess() && null != baseResult.getData()) {
                    List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                    feignTradingRecordOtcReq.setKhUserAccountList(userAccountList);
                }
            }

            BaseResult baseResult = tradingRecordOtcWithdrawFeign.listPage(feignTradingRecordOtcReq);
            if (baseResult.isSuccess() && baseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"),
                        TradingRecordOtcWithdrawResp.class);
                respList.forEach(resp -> setState1Value(resp));
            }
            ExcelUtil.exportExcel(response, respList
                    , getPlatformWithdrawApplyDownloadFileName(req.getCreateTimeStart(), req.getCreateTimeEnd())
                    , APPLY_LIST_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
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

    @SuppressWarnings("unchecked")
    @Override
    public String detail(TradingRecordOtcWithdrawDetailReq req, Model model) {
        FeignTradingRecordOtcWithdrawDetailReq feignTradingRecordOtcDetailReq = new FeignTradingRecordOtcWithdrawDetailReq();
        feignTradingRecordOtcDetailReq.setId(req.getId());
        BaseResult otcRechargeResult = tradingRecordOtcWithdrawFeign.detail(feignTradingRecordOtcDetailReq);

        Map<String, Object> dataMap = (Map<String, Object>) otcRechargeResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        TradingRecordOtcWithdrawResp detailResp = JSONObject.parseObject(jsonResult, TradingRecordOtcWithdrawResp.class);
        setState1Value(detailResp);
        model.addAttribute("detail", detailResp);
        return "wallet/tradingRecord-otcWithdraw-detail";
    }

    private void setState1Value(TradingRecordOtcWithdrawResp resp) {
        if (
                0 == resp.getState1()
                        || 1 == resp.getState1()
                        || 10 == resp.getState1()
        ) {
            resp.setState1Value("处理中");
        }
        if (
                2 == resp.getState1()
        ) {
            resp.setState1Value("成功");
        }
        if (
                3 == resp.getState1()
                        || 4 == resp.getState1()
                        || 9 == resp.getState1()
        ) {
            resp.setState1Value("失败");
        }
        if (resp.getWithdrawMethod() != null) {
            if (1 == resp.getWithdrawMethod()) {
                resp.setWithdrawMethodValue("普通到账");
            }
            if (2 == resp.getWithdrawMethod()) {
                resp.setWithdrawMethodValue("极速到账");
            }
        }
    }
}
