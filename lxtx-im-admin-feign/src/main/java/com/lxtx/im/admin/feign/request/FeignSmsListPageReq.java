package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignSmsListPageReq extends BasePageReq {
    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 发送状态:1代码成功，0代码失败
     */
    private String state;

    /**
     * 开始时间
     */
    private String createTimeStart;
    /**
     *结束时间
     */
    private String createTimeEnd;

}