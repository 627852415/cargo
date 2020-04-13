package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
public class GlobalCodeResp implements Serializable {

    private static final long serialVersionUID = 1L;

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
