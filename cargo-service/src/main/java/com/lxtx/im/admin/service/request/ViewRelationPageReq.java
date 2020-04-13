package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewRelationPageReq extends BasePageReq {

    /**
     * 用户帐号
     */
    private String account;


}
