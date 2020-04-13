package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  修改第三方游戏状态
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class FeignSdkUpdateThirdGameStatusReq {
    /**
     * 游戏id
     */
    private String id;
    /**
     * 游戏状态
     */
    private Boolean status;
}
