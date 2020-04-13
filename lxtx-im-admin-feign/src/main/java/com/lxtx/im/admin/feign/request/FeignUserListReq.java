package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 账号列表
 *
 * @author tangdy
 * @since 2018-08-27
 */
@Getter
@Setter
public class FeignUserListReq extends BasePageReq{
    /**
     *  账号,主键
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
     * 排除显示账号：用于IM用户管理不显示换汇资金账号
     */
    private String excludeAccount;

    private String countryCode;

    /**
     * uid
     */
    private String uid;

    /**
     * 注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchTimeEnd;

}
