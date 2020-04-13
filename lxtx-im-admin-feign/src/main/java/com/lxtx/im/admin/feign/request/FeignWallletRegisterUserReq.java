package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 注册用户参数类
 *
 * @author tangdy
 * @since 2018-08-23
 */
@Getter
@Setter
public class FeignWallletRegisterUserReq {

    private String userId;
    /**
     * 对应平台类型(1: IM)
     */
    private Integer type;
}
