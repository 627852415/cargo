package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 17:23
 */
@Getter
@Setter
public class FeignAdviceFeedbackPageReq extends BasePageReq {
    /**
     * 用户ID
     */
    private String account;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
