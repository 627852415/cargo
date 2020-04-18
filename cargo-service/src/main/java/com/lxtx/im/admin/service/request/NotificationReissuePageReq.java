package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 通知重发参数类
 *
 * @author CaiRH
 * @since 2019-06-12
 */
@Getter
@Setter
public class NotificationReissuePageReq extends BasePageReq {
    /**
     * 类型
     */
    private Integer type;
    /**
     * 处理状态
     */
    private Boolean dealFlag;

    /**
     * 关键字，参数内容搜索
     */
    private String searchKey;

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
