package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 群组管理搜索参数
 *
 * @Author liyunhua
 * @Date 2018-08-30 0030
 */
@Getter
@Setter
public class FeignGroupListPageReq extends BasePageReq {

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 群组名称
     */
    private String name;

    /**
     * 用户帐号
     */
    private String account;


}
