package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/5 13:54
 */
@Getter
@Setter
public class AdviceFeedbackDetailResp implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 表主键
     */
    private String id;

    /**
     * 用户ID
     */
    private String account;

    /**
     * 问题描述
     */
    private String issue;

    /**
     * 图片路径，多个用逗号隔开
     */
    private String images;

    /**
     * 联系人手机号码
     */
    private String telephone;

    /**
     * 手机号码区号
     */
    private String phoneCode;

    /**
     * APP版本号
     */
    private String version;

    /**
     * 区号+电话号码拼接
     */
    private String fullTelephone;
}
