package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignRelationChangeReq {

    /**
     * 被转移用户
     */
    private String changeAccount;


    /**
     * 接受转移用户
     */
    private String acceptAccount;

    /**
     * type=1 转移包括本人及所有下级
     * type=2 转移所有下级，不包括本人
     */
    private Integer changeType;


}
