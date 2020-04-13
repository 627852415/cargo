package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  Feign获取第三方游戏参数类
* @author:   CXM
* @create:   2018-11-30 11:27
*/
@Getter
@Setter
public class FeignSdkThirdGameListReq extends BasePageReq {
    /**
     * 游戏名称
     */
    private String name;

    /**
     * 公司名称
     */
    private String companyName;
}
