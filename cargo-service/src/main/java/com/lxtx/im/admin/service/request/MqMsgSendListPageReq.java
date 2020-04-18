package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 查询mq消息发送重试列表参数类
 *
 * @author CaiRH
 * @since 2020-01-03
 */
@Getter
@Setter
public class MqMsgSendListPageReq extends BasePageReq {

    /**
     * 表主键
     */
    private String id;
    /**
     * 类型
     */
    private Integer type;

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
