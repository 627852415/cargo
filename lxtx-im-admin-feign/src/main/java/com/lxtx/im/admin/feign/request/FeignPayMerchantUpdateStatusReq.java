package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  更新商家状态
* @author:   CXM
* @create:   2019-03-12 16:29
*/
@Setter
@Getter
public class FeignPayMerchantUpdateStatusReq {
    /**
     * 表情包名称
     */
    private String id;

    /**
     * 商家状态，0正常，1禁用
     */
    private Integer status;

}
