package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 异常记录详情参数类
 *
 * @author tangdy
 * @since 2018-08-29
 */
@Getter
@Setter
public class FeignExceptionRecordDetailReq {
    /**
     * 异常记录主键
     */
    private String id;
}
