package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * @author Czh
 * Date: 2019-06-25 16:26
 */
@Setter
@Getter
public class WithdrawConfigSaveOrUpdateReq {

    private String id;

    /**
     * 虚拟币币种
     */
    @NotBlank(message = "coinId不能为空")
    private String coinId;

    /**
     * 最小限额
     */
    @DecimalMin(value = "0",message = "minMoney必须大于等于0")
    private BigDecimal minMoney;

    /**
     * 最大限额
     */
    @DecimalMin(value = "0",message = "maxMoney必须大于等于0")
    private BigDecimal maxMoney;

    /**
     * 普通到账
     */
    private BigDecimal ordinaryArrivalFee;

    /**
     * 快速到账 e.g. 百分之2即0.02
     */
    private BigDecimal quickArrivalFee;
}