package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 更改异常记录状态参数类
 *
 * @author tangdy
 * @since 2018-08-29
 */
@Getter
@Setter
public class FeignExceptionRecordStatusReq {
    /**
     * 异常记录主键
     */
    private String id;
    /**
     * 状态
     */
    private Integer status;
}
