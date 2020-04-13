package com.lxtx.im.admin.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaiRH
 * @date 2018-12-26
 */
@AllArgsConstructor
@Getter
public enum EnumUserCoinAssetSubType {
    //子类型【
    //提币/转账-> 11：站内转账，12：站内提现，13：站外提现，14：资金归集，15：OTC提款，16：平台提款
    //充值/收款-> 21：站内充值，22：站外充值，23：OTC充值
    //游戏-> 31：牛牛（庄闲家保存在备注信息），32：21点；
    //红包-> 41：发红包，42：领红包
    // 】

    /*******************************提币/转账*********************************************/
    /**
     * 站内转账（好友转账）
     */
    INNER_TRANSFER(11, "站内转账"),
    /**
     * 站内提现
     */
    INNER_WITHDRAW(12, "站内提现"),
    /**
     * 站外提现
     */
    OUTSIDE_WITHDRAW(13, "站外提现"),
    /**
     * 资金归集到商户
     */
    CASH_SWEEP(14, "资金归集到商户"),

    /**
     * OTC提款
     */
    OTC_WITHDRAW(15, "OTC提款"),

    /**
     * 平台提款
     */
    PLATFORM_WITHDRAW(16, "平台提款"),

    /**
     * 闪兑
     */
    FEX_WITHDRAW(17, "闪兑"),

    /**
     * 扫码付款
     */
    SCAN_PAY(18, "扫码付款"),

    /**
     * 余额宝转出
     */
    YEB_OUT_WITHDRAW(19, "理财宝转出"),

    /**
     * 余额宝转出手续费
     */
    YEB_OUT_WITHDRAW_FEE(20, "理财宝转出手续费"),


    /**
     * BCB申请表工本费
     */
    BCB_CARD_APPLY_FLAT_COSE_FEE(46, "BCB申请表工本费"),

    /**
     * BCB转出
     */
    BCB_OUT_WITHDRAW(45, "BCB银行转出"),

    /***********************************充值/收款*****************************************/
    /**
     * 站内充值
     */
    INNER_RECHARGE(21, "站内充值"),
    /**
     * 站外充值
     */
    OUTSIDE_RECHARGE(22, "站外充值"),
    /**
     * OTC充值
     */
    OTC_RECHARGE(23, "OTC充值"),

    /**
     * 收款码收款
     */
    SCAN_RECEIPT(24, "收款码收款"),

    /**
     * 余额宝转入
     */
    YEB_INNER_RECHARGE(25, "理财宝转入"),

    /**
     * BCB转入
     */
    BCB_INNER_RECHARGE(26, "BCB银行转入"),


    /**************************************游戏**************************************/
    /**
     * 牛牛
     */
    NIU_NIU(31, "牛牛游戏"),
    /**
     * 21点
     */
    BLACKJACK(32, "21点游戏"),
    /**
     * 游戏分佣
     */
    REBATE(33, "游戏分佣"),

    /**
     * 扫雷游戏
     */
    MINESWEEPER(34, "扫雷游戏"),

    /**************************************红包**************************************/
    /**
     * 发红包
     */
    SEND_RED_PACKET(41, "发红包"),
    /**
     * 领红包
     */
    RECEIVE_RED_PACKET(42, "领红包"),


    /**************************************红包**************************************/
    /**
     * 商户消费
     */
    SDK_ORDER_PAY(61, "商户消费"),
    /**
     * 商户收款
     */
    SDK_ORDER_MERCHANT_RECEIPT(62, "商户收款"),
    /**
     * 商户保证金-扣除
     */
    OFFSITE_EXCHANGE_DEPOSIT_DEDUCT(63, "商户保证金扣除"),
    /**
     * 商户保证金-返还
     */
    OFFSITE_EXCHANGE_DEPOSIT_RETURN(64, "商户保证金返还"),
    /**
     * 商户发布兑换冻结金额-扣除
     */
    OFFSITE_EXCHANGE_FROZEN_AMOUNT_DEDUCT(67, "商户发布兑换冻结金额扣除"),
    /**
     * 商户发布兑换冻结金额-返还
     */
    OFFSITE_EXCHANGE_FROZEN_AMOUNT_RETURN(68, "商户发布兑换冻结金额返还"),
    OFFSITE_BUYER_RECEIVE_AMOUNT(65, "买家换汇资金到账"),
    OFFSITE_MERCHANT_REBATE_AMOUNT(66, "商家换汇返利"),
    ;

    private Integer code;
    private String description;

    public static EnumUserCoinAssetSubType find(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumUserCoinAssetSubType element : EnumUserCoinAssetSubType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    public static String findDescription(Integer code) {
        if (code == null) {
            return null;
        }

        for (EnumUserCoinAssetSubType element : EnumUserCoinAssetSubType.values()) {
            if (element.getCode().equals(code)) {
                return element.getDescription();
            }
        }
        return null;
    }
}
