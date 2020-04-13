package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * @author CaiRH
 */
@Data
public class FeignQueryUsernameReq {

    /**
     * 用户名称
     */
    private String name;
    /**
     * 国际简码
     */
    private String countryCode;

    /**
     * 手机号
     */
    private String telephone;


}
