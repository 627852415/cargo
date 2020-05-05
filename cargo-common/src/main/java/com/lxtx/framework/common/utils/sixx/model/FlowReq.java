package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

/**
 * @author Czh
 * Date: 2018/8/30 下午3:41
 */
@Data
public class FlowReq {

    private Integer userId;
    private Integer type;
    private String startTime;
    private String endTime;
    private Integer page;
    private Integer size;
}