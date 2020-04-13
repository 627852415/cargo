package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class UserResp {

    private String id;

    /**
     * 用户account
     */
    private String account;

    /**
     * 平台用户ID
     */
    private String platformUserId;

    /**
     * 对应平台类型
     */
    private Integer platformType;

    /**
     * 状态【1：创建中，2：正常】
     */
    private Integer state;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;
    /**
     * 全电话
     */
    private String fullTelephone;


    /**
     * 备注
     */
    public String remarks;

    /**
     * 删除标记 0：未删除，1：已删除
     * 不要私有化
     */
    public Boolean delFlag;

    private String countryCode;

}
