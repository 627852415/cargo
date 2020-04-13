package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletOtcOrderStatisticsFeign;
import com.lxtx.im.admin.feign.request.FeignMemberListReq;
import com.lxtx.im.admin.feign.request.FeignOtcOrderDayStatisticsListPageReq;
import com.lxtx.im.admin.feign.request.FeignQueryUserListReq;
import com.lxtx.im.admin.service.OtcOrderDayStatisticsService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.OtcOrderDayStatisticsListPageReq;
import com.lxtx.im.admin.service.response.OtcOrderDayStatisticsListPageResp;
import com.lxtx.im.admin.service.response.OtcOrderDayStatisticsResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OTC买卖比值报表
 *
 * @Author: liyunhua
 * @Date: 2019/3/7
 */
@Service
public class OtcOrderDayStatisticsServiceImpl implements OtcOrderDayStatisticsService {

    @Autowired
    private WalletOtcOrderStatisticsFeign otcOrderStatisticsFeign;

    @Autowired
    private UserFeign userFeign;

    private static final String OTC_SELL_BUY_EXCEL_NAME = "秘密OTC买卖比值报表";


    @Override
    public BaseResult listPage(OtcOrderDayStatisticsListPageReq req) {
        //平台用户查询条件
        String account = req.getAccount();
        String name = req.getName();
        String telephone = req.getTelephone();
        //钱包查询条件
        String userId = req.getUserId();


        //返回参数
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);

        //列表以wallet OTC买卖比值表为主，
        //如果平台用户查询条件不为空，先查平台用户id集合，带到wallet查询
        List<String> accountList = new ArrayList<>();
        if (StringUtils.isNotBlank(account) || StringUtils.isNotBlank(name) || StringUtils.isNotBlank(telephone)) {
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            BeanUtils.copyProperties(req, feignMemberListReq);
            BaseResult coreResult = userFeign.list(feignMemberListReq);
            if (coreResult.isSuccess() && coreResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                List<UserDetailResp> userDetailResps = userListResp.getList();
                if (CollectionUtils.isEmpty(userDetailResps)) {
                    return BaseResult.success();
                }
                if (CollectionUtils.isNotEmpty(userDetailResps)) {
                    for (UserDetailResp user : userDetailResps) {
                        accountList.add(user.getAccount());
                    }
                }
            }
        }

        FeignOtcOrderDayStatisticsListPageReq listPageReq = new FeignOtcOrderDayStatisticsListPageReq();
        BeanUtils.copyProperties(req, listPageReq);
        //平台条件能查询到数据，将平台用户id集合带到钱包查询
        if (CollectionUtils.isNotEmpty(accountList)) {
            listPageReq.setUserIds(accountList);
        }
        //比值查询条件
        String ratioRange = req.getRatioRange();
        String ratioType = req.getRatioType();
        listPageReq.setRatioType(ratioType);
        if (ratioRange.contains("-")) {
            String[] ratioRangeArr = ratioRange.split("-");
            if (ratioRangeArr != null) {
                String ratioMin = ratioRangeArr[0];
                String ratioMax = ratioRangeArr[1];
                listPageReq.setRatioMin(new BigDecimal(ratioMin));
                listPageReq.setRatioMax(new BigDecimal(ratioMax));
            }
        } else {
            if (StringUtils.isNotBlank(ratioRange)) {
                String ratioMin = ratioRange;
                listPageReq.setRatioMin(new BigDecimal(ratioMin));
            }
        }

        //金额查询条件
        String amountRange = req.getAmountRange();
        String amountType = req.getAmountType();
        listPageReq.setAmountType(amountType);
        if (amountRange.contains("-")) {
            String[] amountRangeArr = amountRange.split("-");
            if (amountRangeArr != null) {
                String amountMin = amountRangeArr[0];
                String amountMax = amountRangeArr[1];
                listPageReq.setAmountMin(new BigDecimal(amountMin));
                listPageReq.setAmountMax(new BigDecimal(amountMax));
            }
        } else {
            if (StringUtils.isNotBlank(amountRange)) {
                String amountMin = amountRange;
                listPageReq.setAmountMin(new BigDecimal(amountMin));
            }
        }
        BaseResult walletResult = otcOrderStatisticsFeign.listPage(listPageReq);
        if (walletResult.isSuccess() && walletResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) walletResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            OtcOrderDayStatisticsListPageResp otcOrderListPageResp = JSONObject.parseObject(jsonResult, OtcOrderDayStatisticsListPageResp.class);
            List<OtcOrderDayStatisticsResp> dayStatisticsRespList = otcOrderListPageResp.getRecords();
            //平台用户id集合
            List<String> accountIds = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(dayStatisticsRespList)) {
                for (OtcOrderDayStatisticsResp dayStatisticsResp : dayStatisticsRespList) {
                    accountIds.add(dayStatisticsResp.getAccount());
                }
                if (CollectionUtils.isEmpty(accountIds)) {
                    baseResult.setSuccess(true);
                    baseResult.setData(otcOrderListPageResp);
                    return baseResult;
                }
                //调用imcore接口查询im用户资料,组装IM用户名、手机号码等信息
                assembleUserInfos(accountIds, dayStatisticsRespList);
            }
            baseResult.setSuccess(true);
            baseResult.setData(otcOrderListPageResp);
        }
        return baseResult;
    }

    private void assembleUserInfos(List<String> accountList, List<OtcOrderDayStatisticsResp> dayStatisticsRespList) {
        FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
        queryUserListReq.setIds(accountList);
        BaseResult coreResult = userFeign.queryList(queryUserListReq);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
            String coreJsonResult = JSONArray.toJSONString(coreDataMap);
            UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            if (CollectionUtils.isNotEmpty(userDetailResps)) {
                Map<String, UserDetailResp> userDetailRespMap = new HashMap<>();
                // 封装用户信息
                for (UserDetailResp userResp : userDetailResps) {
                    userDetailRespMap.put(userResp.getAccount(), userResp);
                }
                for (OtcOrderDayStatisticsResp dayStatisticsResp : dayStatisticsRespList) {
                    UserDetailResp userDetailResp = userDetailRespMap.get(dayStatisticsResp.getAccount());
                    if (userDetailResp != null) {
                        dayStatisticsResp.setTelephone(userDetailResp.getTelephone());
                        dayStatisticsResp.setAccount(userDetailResp.getAccount());
                        dayStatisticsResp.setName(userDetailResp.getName());
                    }
                }
            }
        }
    }

    @Override
    public BaseResult generateReport() {
        return otcOrderStatisticsFeign.generateReport();
    }

    @Override
    public void exportExcel(HttpServletResponse response, OtcOrderDayStatisticsListPageReq req) {

        req.setSearchAll(true);
        BaseResult baseResult = listPage(req);

        List<OtcOrderDayStatisticsResp> records = null;
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            OtcOrderDayStatisticsListPageResp resp = JSON.parseObject(
                    JSON.toJSONString(baseResult.getData()),
                    new TypeReference<OtcOrderDayStatisticsListPageResp>() {
                    });
            records = resp.getRecords();

        }

        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }

        for (OtcOrderDayStatisticsResp dayStatisticsResp : records) {
            if (dayStatisticsResp.getDayTotalAmount() != null) {
                dayStatisticsResp.setDayTotalAmount(NumberUtils.keepScale(dayStatisticsResp.getDayTotalAmount(), 2));
            }

            if (dayStatisticsResp.getDayBuyAmount() != null) {
                dayStatisticsResp.setDayBuyAmount(NumberUtils.keepScale(dayStatisticsResp.getDayBuyAmount(), 2));
            }

            if (dayStatisticsResp.getDaySellAmount() != null) {
                dayStatisticsResp.setDaySellAmount(NumberUtils.keepScale(dayStatisticsResp.getDaySellAmount(), 2));
            }

            if (dayStatisticsResp.getDayRatio() != null) {
                dayStatisticsResp.setDayRatio(NumberUtils.keepScale(dayStatisticsResp.getDayRatio(), 2));
            }

            if (dayStatisticsResp.getHistoryTotalAmount() != null) {
                dayStatisticsResp.setHistoryTotalAmount(NumberUtils.keepScale(dayStatisticsResp.getHistoryTotalAmount(), 2));
            }

            if (dayStatisticsResp.getHistoryBuyAmount() != null) {
                dayStatisticsResp.setHistoryBuyAmount(NumberUtils.keepScale(dayStatisticsResp.getHistoryBuyAmount(), 2));
            }
            if (dayStatisticsResp.getHistorySellAmount() != null) {
                dayStatisticsResp.setHistorySellAmount(NumberUtils.keepScale(dayStatisticsResp.getHistorySellAmount(), 2));
            }

            if (dayStatisticsResp.getHistoryRatio() != null) {
                dayStatisticsResp.setHistoryRatio(NumberUtils.keepScale(dayStatisticsResp.getHistoryRatio(), 2));
            }
        }
        ExcelUtil.exportExcel(response, records, OTC_SELL_BUY_EXCEL_NAME, OTC_SELL_BUY_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);

    }


}
