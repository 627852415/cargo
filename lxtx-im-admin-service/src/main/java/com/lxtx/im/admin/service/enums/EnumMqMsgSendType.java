package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumMqMsgSendType {

    DEMO(0, "demo"),
    RETRY(-1, "重试队列"),

    SCAN_PAY_PLATFORM_ACCOUNT_INCOME(1, "扫码付款总账户资金到账MQ"),
    SCAN_PAY_PLATFORM_ACCOUNT_DEDUCT(2, "扫码付款总账户扣款MQ"),
    SCAN_PAY_MERCHANT_INCOME(3, "扫码付款商家到账MQ"),

    REDPACKET_RECEIVE_HANDLE(4, "红包领取消息处理"),

    /**
     * 钱包众币充值订单回调队列
     */
    WALLET_RECHARGE_ZBPAY_ORDER_CALLBACK(5, "众币充值订单回调MQ"),
    /**
     * 钱包众币充值订单定时队列
     */
    WALLET_SYS_RECHARGE_ZBPAY_ORDER_TASK(6, "众币充值订单定时MQ"),
    TRANSFER_FRIENDS_USER_INCOME(7, "好友转账-用户到账MQ"),
    /**
     * tgpay闪兑订单回调队列
     */
    WALLET_FAST_EXCHANGE_TGPAY_ORDER_CALLBACK(8, "tgpay闪兑订单回调MQ"),
    /**
     * tgpay闪兑订单定时队列
     */
    WALLET_SYS_FAST_EXCHANGE_TGPAY_ORDER_TASK(9, "tgpay闪兑订单定时MQ"),
    /**
     * 币支付提现订单定时队列
     */
    WALLET_WITHDRAW_TOPGATE_ORDER_TASK(12, "币支付提现订单定时MQ"),

    /**
     * 币支付提现订单回调队列
     */
    WALLET_WITHDRAW_TOPGATE_ORDER_CALLBACK(13, "币支付提现订单回调MQ"),

    /**
     * 换汇资金总账户资金到账MQ
     */
    OFFSITE_PLATFORM_ACCOUNT_INCOME(14, "换汇-挂单-总账户资金到账MQ"),
    OFFSITE_PLATFORM_ACCOUNT_DEDUCT(15, "换汇-下架挂单-总账户资金扣款MQ"),
    OFFSITE_OFF_GOODS_USER_ACCOUNT_INCOOME(28,"换汇-下架挂单-商家资金加钱MQ"),
    OFFSITE_MERCHANT_DEPOSIT_PLATFORM_USER_INCOME(16,"换汇商家保证金加总账户资金MQ"),
    OFFSITE_MERCHANT_DEPOSIT_PLATFORM_USER_DEDUCT(17,"换汇商家保证金减总账户资金MQ"),
    OFFSITE_BUY_ORDERS_PLATFORM_USER_INCOME(18,"换汇-交易资金-资金冻结-总账户资金转入MQ"),
    OFFSITE_BUY_ORDERS_PLATFORM_USER_DEDUCT(19,"换汇-交易资金-资金解冻-总账户资金转出MQ"),
    OFFSITE_BUY_ORDERS_USER_ACCOUNT_INCOOME(29,"换汇-交易资金-资金解冻-买家加钱MQ"),
    OFFSITE_ORDERS_COMPLETED(20,"换汇-订单成交MQ"),
    OFFSITE_REBATE_PLATFORM_ACCOUNT_DEDUCT(21,"换汇-商家换汇返利-总账户资金转出"),
    OFFSITE_ORDER_MERCHANT_REBATE_INCOME(31,"换汇-商家换汇返利-商家加钱"),
    OFFSITE_BUY_ORDERS_SECRET_PLATFORM_USER_TRANSFER_INCOME(22,"换汇-买家交易资金支出-总账户资金转入"),
    OFFSITE_BUY_ORDERS_SECRET_PLATFORM_USER_TRANSFER_DEDUCT(23,"换汇-商家交易资金到账-总账户资金转出"),
    OFFSITE_BUY_ORDERS_SECRET_MERCHANT_INCOME(24,"换汇-商家交易资金到账"),
    OFFSITE_BUYER_RECEIVE_AMOUNT_PLATFORM_USER_DEDUCT(25,"换汇-换汇资金到账-总账户资金转出"),
    OFFSITE_BUYER_RECEIVE_AMOUNT_USER_ACOOUNT_INCOME(30,"换汇-换汇资金到账-买家加钱"),
    OFFSITE_GOOD_PULL_OFF_MESSAGE(26,"换汇-定时下架挂单"),
    SDK_ORDER_PAY_CALLBACK(27,"SDK-订单支付延迟发送"),
    ;

    private Integer code;
    private String description;

    public static EnumMqMsgSendType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumMqMsgSendType element : EnumMqMsgSendType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}