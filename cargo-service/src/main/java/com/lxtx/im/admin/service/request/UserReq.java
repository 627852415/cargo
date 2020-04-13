package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 会员管理搜索参数
 *
 * @Author liyunhua
 * @Date 2018-08-30 0030
 */
@Getter
@Setter
public class UserReq extends BasePageReq {

    /**
     * 帐号
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
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 账号状态，0:启用，1：禁用，2：冻结
     */
    private String state;

    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 平台用户ID
     */
    private String platformUserId;

    /**
     * 平台类型
     */
    private String platformType;

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 搜索起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTimeBegin;

    /**
     * 搜索结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTimeEnd;
}
