package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignViewRelationPageReq extends BasePageReq{

    /**
     * 用户帐号
     */
    private String account;


}
