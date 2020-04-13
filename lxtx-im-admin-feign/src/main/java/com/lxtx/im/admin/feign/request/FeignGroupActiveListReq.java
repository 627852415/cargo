package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2018-12-28 19:43
 * @Description
 */
@Data
public class FeignGroupActiveListReq  extends BasePageReq{
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
