package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

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
public class FeignCommissionConfigAddToStringReq {

    /**
     *  分佣设置 批量数据
     */
    private String commissionConfigParm;

}
