package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 查询异常记录参数类
 *
 * @author tangdy
 * @since 2018-08-27
 */
@Getter
@Setter
public class ExceptionRecordReq extends BasePageReq {

    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private Integer type;
}
