package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class UserInviteResp {

    /**
     * 用户名
     */
    @ExcelField(name = "用户名", orderBy = "1")
    private String name;

    /**
     * 用户帐号
     */
    @ExcelField(name = "用户帐号", orderBy = "2")
    private String account;

    /**
     * 手机号的国际简码
     */
    @ExcelField(name = "地区", orderBy = "3")
    private String countryCode;

    /**
     * 手机号
     */
    @ExcelField(name = "手机号", orderBy = "4")
    private String telephone;

    /**
     * 直属下级人数
     */
    @ExcelField(name = "直属下级人数", orderBy = "5")
    private Integer lowerNum = 0;

    /**
     * 创建时间
     */
    @ExcelField(name = "注册时间", orderBy = "6")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public String createTime;


}
