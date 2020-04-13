package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  删除第三方游戏参数类
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class FeignSdkThirdGameDeleteReq {
    /**
     * 游戏id
     */
    private String id;
}
