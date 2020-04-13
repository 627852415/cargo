package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class FeignUserAuthenticationUpdateStatusReq {

    /**
     * Id
     */
    private String id;

    /**
     * 状态，0正常，1禁用
     */
    private Integer status;

}
