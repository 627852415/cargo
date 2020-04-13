package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class FeignUserAuthenticationPageReq extends FeignBasePageReq{

    /**
     * 用户ID
     *
     */
    private List<String> accountList;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 认证状态
     */
    private Integer certificateStatus;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isKHShowAccount;

    /**
     * 证件号码
     */
    private String certificateNo;
}
