package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户身份认证状态更新类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class UserAuthenticationUpdateStatusReq {

    /**
     * Id
     */
    private String id;

    /**
     * 状态，0正常，1禁用
     */
    private Integer status;

}
