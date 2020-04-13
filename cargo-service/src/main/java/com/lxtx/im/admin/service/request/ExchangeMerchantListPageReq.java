package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * @Author: liyunhua
 * @Date: 2019/4/23
 */
@Getter
@Setter
public class ExchangeMerchantListPageReq extends BasePageReq {

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 开始交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 认证状态
     */
    private Integer certificateStatus;

    /**
     * 帐号状态
     */
    private Integer status;

    /**
     * 柬埔寨用户account
     */
    private List<String> khUserAccountList;


}
