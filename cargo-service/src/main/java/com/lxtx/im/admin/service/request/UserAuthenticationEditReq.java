package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * <p>
 * 用户身份认证修改请求类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class UserAuthenticationEditReq {

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
