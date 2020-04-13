package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignRebateReportListPageReq extends BasePageReq {

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
    private String createDate;

    /**
     * 结束日期
     */
    private String endDate;

}
