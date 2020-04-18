package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class RebateReportListReq {

    /**
     * 账号,主键
     */
    private String account;

    /**
     * 名称
     */
    private String userName;

    /**
     * 电话
     */
    private String telephone;
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    /**
     * 结束日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

}
