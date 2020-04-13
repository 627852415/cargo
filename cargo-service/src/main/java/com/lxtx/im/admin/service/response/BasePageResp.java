package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Date: 2019/1/10
 */
@Getter
@Setter
public class BasePageResp<T> {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<T> records;
}
