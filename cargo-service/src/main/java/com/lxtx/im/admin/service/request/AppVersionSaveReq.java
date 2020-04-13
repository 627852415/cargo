package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author tangdy
 */
@Setter
@Getter
public class AppVersionSaveReq {

    /**
     * 主键
     */
    private String id;
    /**
     * 版本号
     */
    @NotNull(message = "版本号不能为空")
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
     * 手机类型(0,安卓;1,苹果)
     */
    @NotNull(message = "手机类型不能为空")
    private Integer phoneType;
    /**
     * 下载地址
     */
    private String url;
    /**
     * 是否强制更新(0:否，1：是)
     */
    @NotNull(message = "是否强制更新不能为空")
    private Integer force;

    /**
     * 文件大小
     */
    @NotBlank(message = "是否强制更新不能为空")
    private String size;
}
