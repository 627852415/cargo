package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignGlobalCodeModifyReq {

    private Integer id;
    /**
     * 国家英文名称
     */
    private String enName;
    /**
     * 国家中文名称
     */
    private String cnName;
    /**
     * 国家泰语名称
     */
    private String thName;
    /**
     * 国家繁体名称
     */
    private String twName;
    /**
     * 国家柬埔寨名称
     */
    private String khName;
    /**
     * 国际简码
     */
    private String countryCode;
    /**
     * 手机区号，将old_phone_code的前两个00去除
     */
    private String phoneCode;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 原本的手机区号
     */
    private String oldPhoneCode;

}
