package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/5 16:14
 */
@Getter
@Setter
public class AdviceFeedbackRecordResp {

    public AdviceFeedbackRecordResp(String accountName, String account, String phoneCode,
                                    String telephone, String version, String issue, Date createTime) {
        this.accountName = accountName;
        this.account = account;
        this.phoneCode = phoneCode;
        this.telephone = telephone;
        this.version = version;
        this.issue = issue;
        this.createTime = createTime;
    }

    /**
     * 用户名
     */
    @ExcelField(name = "用户名", orderBy = "1")
    private String accountName;

    /**
     * 用户ID
     */
    @ExcelField(name = "用户ID", orderBy = "2")
    private String account;

    /**
     * 手机号码区号
     */
    @ExcelField(name = "地区", orderBy = "3")
    private String phoneCode;

    /**
     * 联系人手机号码
     */
    @ExcelField(name = "手机号", orderBy = "4")
    private String telephone;

    /**
     * APP版本号
     */
    @ExcelField(name = "版本", orderBy = "5")
    private String version;

    /**
     * 问题描述
     */
    @ExcelField(name = "反馈内容", orderBy = "6")
    private String issue;

    /**
     * 提交时间
     */
    @ExcelField(name = "提交时间", orderBy = "7")
    private Date createTime;
}