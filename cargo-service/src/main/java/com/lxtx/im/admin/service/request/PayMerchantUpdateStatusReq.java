package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  更新商家状态
* @author:   CXM
* @create:   2019-03-12 16:29
*/
@Setter
@Getter
public class PayMerchantUpdateStatusReq {
    /**
     * 表情包名称
     */
    @NotBlank(message = "商家id不能为空")
    private String id;

    /**
     * 商家状态，0正常，1禁用
     */
    private Integer status;

}
