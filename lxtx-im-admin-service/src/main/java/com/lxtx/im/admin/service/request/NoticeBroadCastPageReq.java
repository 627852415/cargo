package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author PengPai
 * Date: Created in 14:54 2020/2/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class NoticeBroadCastPageReq extends BasePageReq {

    private String id;

    //广播标题
    private String title;

    //广播内容
    private String content;

    //国家简码
    private String countryCode;

    //发布状态【0：未发布，1：已发布，2：已撤销】
    private Integer status;

    //查询开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchBeginTime;

    //查询结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchEndTime;

}
