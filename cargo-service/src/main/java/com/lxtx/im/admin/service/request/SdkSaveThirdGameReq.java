package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  保存第三方游戏
* @author:   CXM
* @create:   2018-11-30 13:59
*/
@Setter
@Getter
public class SdkSaveThirdGameReq{
    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    @NotBlank(message = "游戏名称不能为空")
    private String name;
    /**
     * 地址
     */
    @NotBlank(message = "游戏rul不能为空")
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
     * signSecret
     */
    private String appSecret;
    /**
     * 是否禁用（0：否； 1：是）
     */
    private Boolean status;
}
