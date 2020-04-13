package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* @description:  根据id查询第三方游戏信息
* @author:   CXM
* @create:   2018-11-30 13:59
*/
@Setter
@Getter
public class SdkThirdGameInfoResp implements Serializable {

    private static final long serialVersionUID = 1L;

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
