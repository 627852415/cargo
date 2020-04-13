package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取空投入参
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class AirdropReq extends BasePageReq {
    /**
     * 币种名称
     */
    private String coinName;
}
