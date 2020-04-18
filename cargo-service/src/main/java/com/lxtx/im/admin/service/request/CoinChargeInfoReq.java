package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  获取币种手续费信息参数类
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class CoinChargeInfoReq {
    /**
     * 币种手续费ID
     */
    @NotBlank(message = "ID不能为空")
    private String id;
}
