package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取用户信息返回
 * @author: CXM
 * @create: 2018-08-24 18:42
 **/
@Setter
@Getter
public class UserDetailResp {
    /**
     * 账号,主键
     */
    private String account;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 0:女 1：男
     */
    private String gender;
    /**
     * 用户签名
     */
    private String motto;
    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;
    /**
     * 手机号的国际简码
     */
    private String countryCode;

    /**
     * 区号+电话号码拼接，如8613812345678
     */
    private String fullTelephone;

    /**
     * 当前维度
     */
    private Double latitude;
    /**
     * 当前位置信息
     */
    private String location;
    /**
     * 当前经度
     */
    private Double longitude;

    /**
     * 用户头像
     */
    private String userAvatarUrl;
    /**
     * 二维码名片
     */
    private String qrcodeUrl;
}
