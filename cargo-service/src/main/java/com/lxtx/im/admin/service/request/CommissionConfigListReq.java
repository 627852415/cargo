package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


/**
 * <p>
 * 分佣设置
 * </p>
 *
 * @author
 * @since 2020-03-07
 */
@Getter
@Setter
public class CommissionConfigListReq extends BasePageReq {

    /**
     * 分佣类型  1：撮合交易 2：理财宝 3：提现  4：支付
     */
    @NotNull(message = "rebateType不能为空")
    private Integer rebateType;
}
