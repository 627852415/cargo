package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalCodeListPageReq extends BasePageReq {

    /**
     * 国家英文名称
     */
    private String enName;

    /**
     * 国家中文名称
     */
    private String cnName;

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
