package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: liyunhua
 * @Date: 2019/2/26
 */
@Getter
@Setter
public class FeignGroupListReq {

    /**
     * 群id集合
     */
    private List<String> groupIds;

    /**
     * 群id
     */
    private String groupId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群主名称
     */
    private String founder;


}
