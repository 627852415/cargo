package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Data;

import java.util.Date;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2019/12/20 16:55
 */
@Data
public class SalaryInResp {

    private String id;

    @ExcelField(name = "代发工资订单号", orderBy = "1")
    private String salaryOrderNo;

    @ExcelField(name = "用户钱包ID", orderBy = "2")
    private String userId;

    @ExcelField(name = "用户昵称", orderBy = "3")
    private String userName;

    @ExcelField(name = "转入金额", orderBy = "4")
    private String amount;

    @ExcelField(name = "转入币种", orderBy = "5")
    private String coinName;

    private String coinId;

    @ExcelField(name = "转入人国际简码", orderBy = "6")
    private String payCountryCode;

    @ExcelField(name = "转入人手机号", orderBy = "7")
    private String payAccount;

    @ExcelField(name = "转入备注", orderBy = "8")
    private String payRemark;

    private Integer status;

    @ExcelField(name = "订单状态", orderBy = "9")
    private String statusValue;

    @ExcelField(name = "创建时间", orderBy = "10")
    private Date createTime;
    private Date updateTime;
}
