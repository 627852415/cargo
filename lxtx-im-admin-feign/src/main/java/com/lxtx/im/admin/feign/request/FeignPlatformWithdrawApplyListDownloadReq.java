package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
* @description:  申请提款列表下载请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class FeignPlatformWithdrawApplyListDownloadReq {
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
     * 第三方用户ID（eg: IM）
     */
    private List<String> threeUserIdList;
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
