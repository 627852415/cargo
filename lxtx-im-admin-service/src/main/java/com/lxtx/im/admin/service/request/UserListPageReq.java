package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class UserListPageReq extends BasePageReq {
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

    private String countryCode;
}
