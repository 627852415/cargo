package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 保存平台提款配置
 *
 * @author CaiRH
 */
@Setter
@Getter
public class PlatformWithdrawConfigSaveReq {

    /**
     * 主键
     */
    private String id;
    /**
     * 币种ID
     */
    @NotBlank(message = "币种信息不能为空")
    private String coinId;

    /**
     * 有效时间内（单位s秒）
     */
    @NotNull(message = "有效时间内（单位s秒）不能为空")
    private Integer validTime;

    /**
     * 提款限额-最小金额
     */
    @NotNull(message = "最小限额不能为空")
    @DecimalMin(value = "0", message = "最小限额有误")
    private BigDecimal minMoney;
    /**
     * 提款限额-最大金额
     */
    @NotNull(message = "最大限额不能为空")
    @DecimalMin(value = "0", message = "最大限额有误")
    private BigDecimal maxMoney;

    /**
     * 参数值
     */
    @NotNull(message = "手续费不能为空")
    @DecimalMin(value = "0", message = "手续费有误")
    private BigDecimal feeValue;
    /**
     * 手续费方式（1、百分比 2、固定值）
     */
    @NotNull(message = "手续费方式不能为空")
    @Max(value = 2, message = "无效的手续费方式")
    @Min(value = 1, message = "无效的手续费方式")
    private Integer feeType;

    /**
     * 是否显示【0：默认不显示；1：显示】
     */
    private Boolean display;
    /**
     * 排序权重【从大到小】
     */
    private Integer idx;
}
