package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 获取OTC提款配置
 *
 * @author CaiRH
 * @since 2019-06-24
 */
@Getter
@Setter
public class OtcWithdrawConfigResp implements Serializable {

    private String id;

    /**
     * 虚拟币币种
     */
    private String coinName;
    /**
     * 虚拟币币种
     */
    private String coinId;

    /**
     * 最小限额
     */
    private String minMoney;

    /**
     * 最大限额
     */
    private String maxMoney;

    /**
     * 普通到账
     */
    private String ordinaryArrivalFee;

    /**
     * 快速到账 e.g. 百分之2即0.02
     */
    private String quickArrivalFee;

}
