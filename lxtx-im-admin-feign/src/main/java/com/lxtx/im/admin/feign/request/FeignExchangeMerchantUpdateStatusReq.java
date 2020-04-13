package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: liyunhua
 * @Date: 2019/4/24
 */
@Setter
@Getter
public class FeignExchangeMerchantUpdateStatusReq {

    /**
     * 商家id
     */
    private String id;

    /**
     * 商家状态，0正常，1禁用
     */
    private Integer status;

}
