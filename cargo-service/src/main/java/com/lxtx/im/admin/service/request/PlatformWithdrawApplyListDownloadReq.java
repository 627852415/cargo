package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 系统提款申请列表下载请求
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class PlatformWithdrawApplyListDownloadReq {
    /**
     * 流水号
     */
    private String id;

    /**
     * 币种ID
     */
    private String coinId;
    /**
     * 提款申请状态
     */
    private Integer status;
    /**
     * 手机号码
     */
    private String telephone;
    /**
     * 银行卡号
     */
    private String account;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
