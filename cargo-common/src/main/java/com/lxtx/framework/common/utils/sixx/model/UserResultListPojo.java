package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

import java.util.List;

/**
 *
 * @author: Czh
 * Date: 2018/8/15 下午4:37
 */
@Data
public class UserResultListPojo {

    private Integer total;

    private Integer size;

    private Integer count;

    private Integer page;

    private List<UserResultPojo> data;

}
