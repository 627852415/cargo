package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  保存第三方游戏
* @author:   CXM
* @create:   2018-11-30 13:59
*/
@Setter
@Getter
public class FeignSdkSaveThirdGameReq {
    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String url;
    /**
     * logo
     */
    private String icon;
    /**
     * 企业工商编号
     */
    private String companyId;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * appKey
     */
    private String appKey;
    /**
     * appSecret
     */
    private String appSecret;
    /**
     * 是否禁用（0：否； 1：是）
     */
    private Boolean status;
}
