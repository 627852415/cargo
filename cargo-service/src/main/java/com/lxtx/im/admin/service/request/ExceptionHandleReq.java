package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 查询异常记录参数类
 *
 * @author tangdy
 * @since 2018-08-27
 */
@Getter
@Setter
public class ExceptionHandleReq{

    @NotBlank(message = "数据异常")
    private String id;

    /**
     * 类型
     */
    @NotBlank(message = "数据异常")
    private Integer type;
}
