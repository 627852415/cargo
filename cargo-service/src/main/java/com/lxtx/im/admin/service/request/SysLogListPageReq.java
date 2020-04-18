package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysLogListPageReq extends BasePageReq {

    /**
     * 账号,主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 创建时间开始
     */
    private String createTimeStart;

    /**
     * 创建时间结束
     */
    private String createTimeEnd;

    /**
     * 操作
     */
    private String operation;

    /**
     * IP
     */
    private String ip;
}
