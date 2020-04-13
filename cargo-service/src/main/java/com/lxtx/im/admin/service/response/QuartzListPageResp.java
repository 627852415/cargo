package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @description:  定时任务列表分页返回
* @author:   CXM
* @create:   2018-10-13 16:55
*/
@Getter
@Setter
public class QuartzListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<QuartzResp> records;
}
