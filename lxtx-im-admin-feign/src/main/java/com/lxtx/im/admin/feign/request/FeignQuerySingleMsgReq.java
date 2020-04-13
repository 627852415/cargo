package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-05-24 11:42
 * @Description
 */
@Data
public class FeignQuerySingleMsgReq {
    /**
     * 消息接收者账号
     */

    private String friendAccount;
    /**
     * 消息发送者账号
     */
    private String account;

    private String action;

    private Long startTimeMillis;

    private Long endTimeMillis;


    /**
     * 页码
     */
    private Integer current;
    /**
     * 长度
     */
    private Integer size = 1000 ;
}
