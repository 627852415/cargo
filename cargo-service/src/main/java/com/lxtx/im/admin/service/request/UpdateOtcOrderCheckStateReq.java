package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * otc订单类
 *
 * @author LiuLP
 * @since 2018-09-27
 */
@Getter
@Setter
public class UpdateOtcOrderCheckStateReq extends BasePageReq {


    /**
     * 主键
     */
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 审核状态
     */
    @NotNull(message = "checkState不能为空")
    @Min(value = 1, message = "checkState最小为1")
    @Max(value = 2, message = "checkState最大为2")
    private Integer checkState;


}
