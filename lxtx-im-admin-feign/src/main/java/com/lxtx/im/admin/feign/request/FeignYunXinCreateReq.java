package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2018-11-17 13:07
 * @Description
 */
@Getter
@Setter
public class FeignYunXinCreateReq {

    private String account;

    private String token;
}
