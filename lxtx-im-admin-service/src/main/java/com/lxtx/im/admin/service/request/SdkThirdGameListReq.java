package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;


/**
* @description:  获取第三方游戏参数类
* @author:   CXM
* @create:   2018-11-30 11:27
*/
@Getter
@Setter
public class SdkThirdGameListReq extends BasePageReq {
    /**
     * 游戏名称
     */
    private String name;

    /**
     * 公司名称
     */
    private String companyName;
}
