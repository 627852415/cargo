package com.lxtx.im.admin.service.request;

import lombok.Data;


/**
 * <p>
 *   群活跃的统计请求参数
 * </p>
 *
 * @author liboyan
 * @Date 2018-12-20 15:15
 * @Description
 */
@Data
public class GroupActiveListReq extends BasePageReq {

    /**
     * 搜索类型可以分为 日、周、月
     * */
    private Integer searchType;

    /**
     * 群ID
     * */
    private String groupId;

    /**
     * 群名称
     * */
    private String groupName;

    /**
     * 搜索时间范围
     * */
    private String searchDate;

}
