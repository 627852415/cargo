package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class WalletUserResp {

    /**
     * 用户ID
     */
    private String id;

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
    private Integer status;

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
     * 手机号的国际简码
     */
    private String countryCode;


    /**
     * 备注
     */
    public String remarks;
    /**
     * 创建时间
     */
    public Date createTime;
    /**
     * 创建人
     */
    public String createBy;
    /**
     * 更新时间
     */
    public Date updateTime;
    /**
     * 更新人
     */
    public String updateBy;
    /**
     * 删除标记 0：未删除，1：已删除
     * 不要私有化
     */
    public Boolean delFlag;

}
