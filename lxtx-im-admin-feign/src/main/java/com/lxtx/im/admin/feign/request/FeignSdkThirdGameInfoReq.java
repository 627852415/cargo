package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  根据id获取第三方游戏信息
* @author:   CXM
* @create:   2018-11-30 13:59
*/
@Setter
@Getter
public class FeignSdkThirdGameInfoReq {
    /**
     * 游戏id
     */
    private String id;
}
