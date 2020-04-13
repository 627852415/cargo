package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tangdy
 */
@Getter
@Setter
public class AppVersionInfoResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 版本号
     */
    private Integer version;
    /**
     * 版本名称
     */
    private String versionName;
    /**
     * 更新内容
     */
    private String description;

    /**
     * 下载地址
     */
    private String url;
    /**
     * 是否强制更新(0:否，1：是)
     */
    private Integer force;

    /**
     * 手机类型(0:安卓，1：IOS)
     */
    private Integer phoneType;

    /**
     * 文件大小
     */
    private String size;
}
