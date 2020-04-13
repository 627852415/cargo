package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分佣订单详情表
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Getter
@Setter
public class FeignCommissionOrderDetailReq extends BasePageReq{

    /**
     * ID
     */
    private String id;

}
