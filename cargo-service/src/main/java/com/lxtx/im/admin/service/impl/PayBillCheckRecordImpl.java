package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.PayBillCheckRecordFeign;
import com.lxtx.im.admin.feign.feign.PayOrderFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.PayBillCheckRecordService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @description: 对账列表实现类
 * @author: Ppai
 * @create: 2018-03-12 09:58
 **/
@Service
public class PayBillCheckRecordImpl implements PayBillCheckRecordService {

    @Autowired
    private PayBillCheckRecordFeign payBillCheckRecordFeign;

    @Autowired
    private PayOrderFeign payOrderFeign;

    @Autowired
    private UserFeign userFeign;

    private static final String BILL_CHECK_EXCEL_NAME = "商户对账记录表";

    private static final String BILL_DETAIL_EXCEL_NAME = "商户对账详情表";

    private static final String SETTLE_FEE_EDIT_ERROR_MESSAGE = "费率格式不正确";

    /**
     * 对账列表
     *
     * @param payBillCheckRecordIndexReq
     * @return
     */
    @Override
    public BaseResult indexList(PayBillCheckRecordIndexReq payBillCheckRecordIndexReq) {
        FeignPayBillCheckRecordIndexReq feignPayBillCheckRecordIndexReq = new FeignPayBillCheckRecordIndexReq();
        BeanUtils.copyProperties(payBillCheckRecordIndexReq, feignPayBillCheckRecordIndexReq);
        BaseResult baseResult = payBillCheckRecordFeign.listPage(feignPayBillCheckRecordIndexReq);
        List<PayBillCheckRecordResp> records = null;
        PayBillCheckRecordPageResp payBillCheckRecordPageResp = null;
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            payBillCheckRecordPageResp = JSON.parseObject(
                    JSON.toJSONString(baseResult.getData()),
                    new TypeReference<PayBillCheckRecordPageResp>() {
                    });
            records = payBillCheckRecordPageResp.getRecords();
        }

        if (CollectionUtils.isEmpty(records)) {
            return BaseResult.success();
        }

        for (PayBillCheckRecordResp payBillCheckRecordResp : records) {
            String platformUserId = payBillCheckRecordResp.getPlatformUserId();
            FeignUserReq feignUserReq = new FeignUserReq();
            feignUserReq.setAccount(platformUserId);
            BaseResult baseResult1 = userFeign.get(feignUserReq);
            if (baseResult1.isSuccess()) {
                Map<String, Object> UserResp = (Map<String, Object>) baseResult1.getData();
                String telephone = (String) UserResp.get("telephone");
                payBillCheckRecordResp.setPhoneNum(telephone);
            }
        }
        payBillCheckRecordPageResp.setRecords(records);
        baseResult.setData(payBillCheckRecordPageResp);
        return baseResult;
    }

    /**
     * 修改结算周期及币种手续费比率
     *
     * @param payCoinRateListReq
     * @return
     */
    @Override
    public BaseResult editRate(PayCoinRateListReq payCoinRateListReq) {

        FeignPayBillCheckRecordEditReq feignPayBillCheckRecordEditReq = new FeignPayBillCheckRecordEditReq();
        BeanUtils.copyProperties(payCoinRateListReq, feignPayBillCheckRecordEditReq);
        List<PayCoinRateReq> coinList = payCoinRateListReq.getCoinList();
        if (CollectionUtils.isNotEmpty(coinList)) {
            for (PayCoinRateReq payCoinRateReq : coinList) {
                String feeRate = payCoinRateReq.getFeeRate();
                if (StringUtils.isBlank(feeRate)) {
                    continue;
                }
                String pattern = "[\\d.]+%";
                if (Pattern.matches(pattern, feeRate)) {
                    String[] split = StringUtils.split(feeRate, "%");
                    String fee = split[0];
                    BigDecimal bd = new BigDecimal(fee);
                    BigDecimal divide = bd.divide(new BigDecimal(100));
                    String fe = divide.toString();
                    payCoinRateReq.setFeeRate(fe);
                } else {
                    return BaseResult.error("100", SETTLE_FEE_EDIT_ERROR_MESSAGE);
                }
            }
        }
        BaseResult baseResult = payBillCheckRecordFeign.editCyleAndRate(feignPayBillCheckRecordEditReq);
        return baseResult;
    }

    /**
     * 查询修改信息
     *
     * @param payCoinRateListReq
     * @return
     */
    @Override
    public BaseResult preEdit(PayCoinRateListReq payCoinRateListReq) {
        FeignPayBillCheckRecordEditReq feignPayBillCheckRecordEditReq = new FeignPayBillCheckRecordEditReq();
        BeanUtils.copyProperties(payCoinRateListReq, feignPayBillCheckRecordEditReq);

        BaseResult baseResult = payBillCheckRecordFeign.preEdit(feignPayBillCheckRecordEditReq);
        return baseResult;
    }


    @Override
    public BaseResult billListPage(BillListReq req) {

        FeignBillListReq feignReq = new FeignBillListReq();
        BeanUtils.copyProperties(req, feignReq);

        return payBillCheckRecordFeign.billListPage(feignReq);
    }

    @Override
    public BaseResult payOrderListPage(BillListReq req) {
        FeignBillListReq feignReq = new FeignBillListReq();
        BeanUtils.copyProperties(req, feignReq);

        return payOrderFeign.payOrderListPage(feignReq);
    }

    @Override
    public BaseResult checkDetail(PayCheckDetailReq req) {
        String start = req.getStart();
        String end = req.getEnd();
        Date startDate = convertStrToDate(start);
        Date endDate = convertStrToDate(end);
        FeignPayCheckDetailReq fcdr = new FeignPayCheckDetailReq();
        BeanUtils.copyProperties(req, fcdr);
        fcdr.setStart(startDate);
        fcdr.setEnd(endDate);
        return payBillCheckRecordFeign.checkDetail(fcdr);
    }

    /**
     * 对账列表导出
     *
     * @param response
     * @param payBillCheckRecordIndexReq
     */
    @Override
    public void exportListExcel(HttpServletResponse response, PayBillCheckRecordIndexReq payBillCheckRecordIndexReq) {
        payBillCheckRecordIndexReq.setSearchAll(true);
        BaseResult baseResult = indexList(payBillCheckRecordIndexReq);
        List<PayBillCheckRecordResp> records = null;
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            PayBillCheckRecordPageResp payBillCheckRecordPageResp = JSON.parseObject(
                    JSON.toJSONString(baseResult.getData()),
                    new TypeReference<PayBillCheckRecordPageResp>() {
                    });
            records = payBillCheckRecordPageResp.getRecords();
        }
        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }

        for (PayBillCheckRecordResp payBillCheckRecordResp : records) {
            String fee = payBillCheckRecordResp.getFee();
            if (StringUtils.isNotBlank(fee)) {
                List<PayCoinRateResp> payCoinRateResps = JSONObject.parseArray(
                        fee,
                        PayCoinRateResp.class);
                StringBuilder sb = new StringBuilder();
                if (CollectionUtils.isEmpty(payCoinRateResps)) {
                    continue;
                }
                for (PayCoinRateResp payCoinRateResp : payCoinRateResps) {
                    String coinName = payCoinRateResp.getCoinName();
                    String feeRate = payCoinRateResp.getFeeRate();

                    if (StringUtils.isNotBlank(coinName) && StringUtils.isNotBlank(feeRate)) {
                        sb.append(coinName + ":").append(feeRate + " ");
                    }
                }
                payBillCheckRecordResp.setFee(sb.toString());
            }
        }

        ExcelUtil.exportExcel(response, records, BILL_CHECK_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM);

    }

    @Override
    public void exportDetailExcel(HttpServletResponse response, PayCheckDetailReq req) {
        req.setSearchAll(true);
        BaseResult baseResult = checkDetail(req);
        List<PayCheckDetailResp> records = null;
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            PayCheckDetailPageResp payCheckDetailPageResp = JSON.parseObject(
                    JSON.toJSONString(baseResult.getData()),
                    new TypeReference<PayCheckDetailPageResp>() {
                    });
            records = payCheckDetailPageResp.getRecords();
        }
        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        for (PayCheckDetailResp payCheckDetailResp : records) {
            String amount = payCheckDetailResp.getAmount();
            if (StringUtils.isBlank(amount)) {
                continue;
            }
            List<PayCoinAmountResp> payCoinAmountResp = JSONObject.parseArray(amount,
                    PayCoinAmountResp.class);
            StringBuilder sb = new StringBuilder();
            if (CollectionUtils.isEmpty(payCoinAmountResp)) {
                continue;
            }
            for (PayCoinAmountResp payCoinAmount : payCoinAmountResp) {
                String coinName = payCoinAmount.getCoinName();
                String amountStr = payCoinAmount.getAmount();
                if (StringUtils.isNotBlank(coinName) && StringUtils.isNotBlank(amountStr)) {
                    sb.append(coinName + ":").append(amountStr + " ");
                }
            }
            payCheckDetailResp.setAmount(sb.toString());
        }
        ExcelUtil.exportExcel(response, records, BILL_DETAIL_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM);
    }

    private Date convertStrToDate(String timeStr) {
        Date parse = null;
        if (StringUtils.isNotBlank(timeStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                parse = sdf.parse(timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return parse;
    }
}
