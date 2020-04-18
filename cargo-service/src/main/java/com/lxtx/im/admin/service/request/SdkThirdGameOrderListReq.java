package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  获取第三方游戏订单参数类
* @author:   CXM
* @create:   2018-11-30 11:27
*/
@Getter
@Setter
public class SdkThirdGameOrderListReq extends BasePageReq {
    /**
     * 订单ID
     */
    private String id;

    /**
     * 币种ID
     */
    private String coinId;

    private Integer status;
}
