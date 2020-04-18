package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tangdy
 */
@Getter
@Setter
public class AppVersionListPageReq extends BasePageReq {
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
     * 是否强制更新(0:否，1：是)
     */
    private Integer force;

    /**
     * 手机类型(0,安卓;1,苹果)
     */
    private Integer phoneType;
}
