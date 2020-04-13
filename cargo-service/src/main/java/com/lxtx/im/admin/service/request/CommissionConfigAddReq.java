package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 分佣设置表
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Setter
@Getter
public class CommissionConfigAddReq {

    /**
     * ID
     */
    private String id;

    /**
     * 分佣类型  1：撮合交易 2：理财宝 3：提现  4：支付
     */
    @NotNull(message = "rebateType不能为空")
    private Integer rebateType;
    /**
     * 分佣级别ID
     */
    @NotBlank(message = "levelId能为空")
    private String levelId;
    /**
     * 分佣级别名称
     */
    @NotBlank(message = "levelName不能为空")
    private String levelName;
    /**
     * 级别分佣比例
     */
    @NotNull(message = "rebateType不能为空")
    private Float levelRebateRatio;
    /**
     * 平台分佣比例
     */
    private Float platformRebateRatio;


}
