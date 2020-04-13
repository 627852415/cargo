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
public class FeignUserAuthenticationEditReq {

    /**
     * id
     *
     */
    private String id;

    /**
     * 姓氏
     */
    private String lastName;

    /**
     * 名字
     */
    private String firstName;

    /**
     * 性别【0:女 1：男，2：未知】
     */
    private String genderName;

    /**
     * 国际简码
     */
    private String countryCode;

    /**
     * 证件号码
     */
    private String certificateNo;

}
