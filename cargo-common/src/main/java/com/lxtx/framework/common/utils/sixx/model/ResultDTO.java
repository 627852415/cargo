package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

/**
 *
 * @author: Czh
 * Date: 2018/8/15 下午4:37
 */
@Data
public class ResultDTO<T> {

    private String codeEnum;

    private String message;

    private T data;

    private Boolean flag;

}
