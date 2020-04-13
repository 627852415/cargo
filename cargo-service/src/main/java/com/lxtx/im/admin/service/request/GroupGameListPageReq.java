package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * 群游戏列表请求参数
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Setter
@Getter
public class GroupGameListPageReq extends BasePageReq {

    /**
     * 群id
     */
    private String groupId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群主帐号
     */
    private String founder;


}
