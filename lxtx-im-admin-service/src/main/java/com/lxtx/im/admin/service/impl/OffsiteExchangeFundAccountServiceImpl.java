package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.feign.request.FeignUserDetailReq;
import com.lxtx.im.admin.feign.request.FeignWalletUserInfoReq;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.OffsiteExchangeFundAccountService;
import com.lxtx.im.admin.service.OffsiteExchangeOrderService;
import com.lxtx.im.admin.service.OffsiteExchangeRebateService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.StatisticsOrderReq;
import com.lxtx.im.admin.service.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/10 16:24
 */
@Service
@Slf4j
public class OffsiteExchangeFundAccountServiceImpl implements OffsiteExchangeFundAccountService {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    private WalletUserCoinFeign walletUserCoinFeign;
    @Autowired
    private OffsiteExchangeRebateService offsiteExchangeRebateService;
    @Autowired
    private OffsiteExchangeOrderService offsiteExchangeOrderService;

    /**
     * 获取资金池账号详情
     *
     * @return
     */
    @Override
    public OffsiteExchangeFundAcountResp getFundAccountInfo() {
        DecimalFormat df = new DecimalFormat("0.00000000");
        //查询字典配置的资金池账号信息
        String walletUserId = dictService.getDictValue(DictConstants.DICT_DOMAIN_OFFSITE_EXCHANGE,
                DictConstants.DICT_KEY_OFFSITE_CAPITAL_USERID);
        if (!StringUtils.isNotBlank(walletUserId)) {
            throw LxtxBizException.newException("资金账号字典不存在!");
        }
        //根据资金池账号ID查询钱包用户账户信息
        FeignWalletUserInfoReq walletUserReq = new FeignWalletUserInfoReq();
        walletUserReq.setUserId(walletUserId);
        BaseResult walletUser = walletUserFeign.queryWalletUserById(walletUserReq);
        if (!walletUser.isSuccess() || walletUser.getData() == null) {
            throw LxtxBizException.newException("钱包用户不存在!");
        }
        String walletUserJson = JSONObject.toJSONString(walletUser.getData());
        WalletUserResp walletUserResp = JSONObject.parseObject(walletUserJson, WalletUserResp.class);
        //根据钱包用户表的platform_user_id字段查找IM用户表信息
        FeignUserDetailReq feignUserDetailReq = new FeignUserDetailReq();
        feignUserDetailReq.setAccount(walletUserResp.getPlatformUserId());
        BaseResult imUserResult = userFeign.detail(feignUserDetailReq);
        if (!imUserResult.isSuccess() || imUserResult.getData() == null) {
            throw LxtxBizException.newException("资金账号不存在!");
        }
        String userJsonResult = JSONObject.toJSONString(imUserResult.getData());
        UserDetailResp userDetailResp = JSONObject.parseObject(userJsonResult, UserDetailResp.class);
        String fullTelephone = StringUtils.isNotBlank(userDetailResp.getFullTelephone()) ? userDetailResp
                .getFullTelephone() : "";//获取平台用户区号+电话号码
        List<String> coinIdList = new ArrayList<>();
        //换汇总收入
        BigDecimal exchangeIncome = BigDecimal.ZERO, exchangePayment = BigDecimal.ZERO, totalProfits = BigDecimal.ZERO;
        int totalOrders = 0;
        FeignStatisticsOrderReq orderReq = new FeignStatisticsOrderReq();
        orderReq.setIsAll(true);
        BaseResult amountResult = offsiteExchangeRebateService.getRebateAmount(orderReq);
        if (amountResult.isSuccess() && amountResult.getData() != null) {
            String amountResultJson = JSONObject.toJSONString(amountResult.getData());
            RebateAmountResp amountResp = JSONObject.parseObject(amountResultJson, RebateAmountResp.class);
            coinIdList = amountResp.getCoinIdList();
            exchangePayment = amountResp.getMerchantRebateAmount();
            totalProfits = amountResp.getPlatformRebateAmount();
        }
        //获取资金账号余额列表
        FeignWalletUserInfoReq coinReq = new FeignWalletUserInfoReq();
        coinReq.setUserId(walletUserId);
        coinReq.setCoinIdList(coinIdList);
        BaseResult coinResult = walletUserCoinFeign.queryWalletUserCoinListByUserId(coinReq);
        List<String> accountAmountList = null;
        if (coinResult.isSuccess() && coinResult.getData() != null) {
            Map<String, Object> coinMap = (Map<String, Object>) coinResult.getData();
            accountAmountList = (List<String>) coinMap.get("list");
        }
        //获取订单统计数据
        BaseResult orderResult = offsiteExchangeOrderService.getOrder(orderReq);
        BigDecimal totalPayAmount = BigDecimal.ZERO;
        if (orderResult.isSuccess() && orderResult.getData() != null) {
            Map<String, Object> orderMap = (Map<String, Object>) orderResult.getData();

            List<OffsiteExchangeOrderManageDetail> orderList = JSON.parseArray(JSON.toJSONString(orderMap
                    .get(BaseResult.LIST)), OffsiteExchangeOrderManageDetail.class);
            if (!CollectionUtils.isEmpty(orderList)) {
                totalOrders = orderList.size();
                exchangeIncome = orderList.stream().map(OffsiteExchangeOrderManageDetail::getTargetAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
//                totalPayAmount = orderList.stream().map(o -> NumberUtils.divide(o.getSourceAmount(),
//                        o.getExchangeRate(), 8)
//                ).reduce(BigDecimal.ZERO, BigDecimal::add);
                totalPayAmount = orderList.stream().map(OffsiteExchangeOrderManageDetail::getSourceAmount
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
        }
        OffsiteExchangeFundAcountResp fundAccount = new OffsiteExchangeFundAcountResp();
        fundAccount.setFullTelephone(fullTelephone);
        fundAccount.setAccountAmount(JSON.toJSONString(accountAmountList));
        fundAccount.setExchangeIncome(exchangeIncome != null ? df.format(exchangeIncome) + "" : "0");//换汇总收入
        fundAccount.setExchangePayment(df.format(totalPayAmount) + "");//换汇总支出
        fundAccount.setTotalProfits(totalProfits != null ? df.format(totalProfits) + "" : "0");//总利润
        fundAccount.setTotalOrders(totalOrders + "");//累计成交订单数
        fundAccount.setWalletUserId(walletUserId);
        return fundAccount;
    }

    @Override
    public OffsiteExchangeFundAcountResp getFundAccountInfoByDate(StatisticsOrderReq statisticsOrderReq) {
        DecimalFormat df = new DecimalFormat("0.00000000");
        //获取订单统计数据
        FeignStatisticsOrderReq orderReq = new FeignStatisticsOrderReq();
        BeanUtils.copyProperties(statisticsOrderReq, orderReq);
        orderReq.setIsAll(false);
        BaseResult orderResult = offsiteExchangeOrderService.getOrder(orderReq);
        int totalOrders = 0, successOrders = 0;
        BigDecimal exchangeIncome = BigDecimal.ZERO, totalPayAmount = BigDecimal.ZERO;
        if (orderResult.isSuccess() && orderResult.getData() != null) {
            Map<String, Object> orderMap = (Map<String, Object>) orderResult.getData();
            List<OffsiteExchangeOrderManageDetail> orderList = JSON.parseArray(JSON.toJSONString(orderMap
                    .get(BaseResult.LIST)), OffsiteExchangeOrderManageDetail.class);
            List<OffsiteExchangeOrderManageDetail> sucessOrderList;
            if (!CollectionUtils.isEmpty(orderList)) {
                //总的订单数
                totalOrders = orderList.size();
                //成交的订单集合
                sucessOrderList = orderList.stream().filter(o -> o.getStatus() == 2).collect(Collectors.toList());
                //成交订单数
                successOrders = sucessOrderList.size();
                //总收入
                exchangeIncome = sucessOrderList.stream().map(OffsiteExchangeOrderManageDetail::getTargetAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                //总的支出
//                totalPayAmount = sucessOrderList.stream().map(o -> {
//                    if (o.getExchangeRate().compareTo(BigDecimal.ZERO) == 0) {
//                        return o.getSourceAmount();
//                    } else {
//                        return NumberUtils.divide(o.getSourceAmount(), o.getExchangeRate(), 8);
//                    }
//                }).reduce(BigDecimal.ZERO, BigDecimal::add);
                totalPayAmount = sucessOrderList.stream().map(OffsiteExchangeOrderManageDetail::getSourceAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }
        }

        BigDecimal merchantRebateAmount = BigDecimal.ZERO, totalProfits = BigDecimal.ZERO;
        BigDecimal totalInviteAmount = BigDecimal.ZERO;
        BaseResult amountResult = offsiteExchangeRebateService.getRebateAmount(orderReq);
        if (amountResult.isSuccess() && amountResult.getData() != null) {
            String amountResultJson = JSONObject.toJSONString(amountResult.getData());
            RebateAmountResp amountResp = JSONObject.parseObject(amountResultJson, RebateAmountResp.class);
            //总的商家返利
            merchantRebateAmount = amountResp.getMerchantRebateAmount();
            //总的利润
            totalProfits = amountResp.getPlatformRebateAmount();
            totalInviteAmount = amountResp.getTotalInviteAmount();
        }
        //totalPayAmount = NumberUtils.add(totalPayAmount, merchantRebateAmount);
        OffsiteExchangeFundAcountResp fundAccount = new OffsiteExchangeFundAcountResp();
        fundAccount.setExchangeIncome(exchangeIncome != null ? df.format(exchangeIncome) + "" : "0");//换汇总收入
        fundAccount.setExchangePayment(df.format(totalPayAmount) + "");//换汇总支出
        fundAccount.setTotalProfits(totalProfits != null ? df.format(totalProfits) + "" : "0");//总利润
        fundAccount.setTotalOrders(totalOrders + "");//累计成交订单数
        fundAccount.setSuccessOrders(successOrders + "");
        fundAccount.setMerchantRebateAmount(merchantRebateAmount != null ? df.format(merchantRebateAmount) + ""
                : "0");
        fundAccount.setInviteAmount(totalInviteAmount != null ? df.format(totalInviteAmount) + "" : "0");
        return fundAccount;
    }
}
