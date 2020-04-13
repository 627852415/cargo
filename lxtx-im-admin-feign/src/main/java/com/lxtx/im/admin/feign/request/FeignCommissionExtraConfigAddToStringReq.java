package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 额外分佣配置表
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Getter
@Setter
public class FeignCommissionExtraConfigAddToStringReq {

    /**
     *  白名单批量设置 批量数据
     */
    @NotNull(message = "commissionExtraConfigParm不能为空")
    private String commissionExtraConfigParm;


}
