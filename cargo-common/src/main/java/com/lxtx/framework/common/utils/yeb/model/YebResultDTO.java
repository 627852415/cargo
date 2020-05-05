package com.lxtx.framework.common.utils.yeb.model;

import lombok.Data;

/**
 * author lin hj on 2019/3/29
 */
@Data
public class YebResultDTO<T> {

    private Integer code;
    private String message;
    private T data;

}
