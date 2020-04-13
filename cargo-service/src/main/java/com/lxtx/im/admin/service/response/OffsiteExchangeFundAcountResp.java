package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/11 10:14
 */
@Getter
@Setter
public class OffsiteExchangeFundAcountResp {

    /**
     * 区号+电话号码
     */
    private String fullTelephone;

    /**
     * 账号余额列表
     */
    String accountAmount;

    /**
     * 换汇总收入
     */
    private String exchangeIncome;

    /**
     * 换汇总支出
     */
    private String exchangePayment;

    /**
     * 总利润
     */
    private String totalProfits;

    /**
     * 总的订单数
     */
    private String totalOrders;

    /**
     * 成交订单数
     */
    private String successOrders;

    /**
     * 商家返利
     */
    private String merchantRebateAmount;

    private String walletUserId;
    /**
     * 邀请总返利
     */
    private String inviteAmount;

}
