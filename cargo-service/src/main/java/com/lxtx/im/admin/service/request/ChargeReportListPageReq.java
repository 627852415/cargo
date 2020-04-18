package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 手续费报表请求参数
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Setter
@Getter
public class ChargeReportListPageReq extends BasePageReq {

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
