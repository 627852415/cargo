package com.lxtx.im.admin.feign.request;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignAppVersionSaveReq {

    /**
     * 主键
     */
    @TableId
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
     * 手机类型(0,安卓;1,苹果)
     */
    private Integer phoneType;
    /**
     * 下载地址
     */
    private String url;
    /**
     * 是否强制更新(0:否，1：是)
     */
    private Integer force;

    /**
     * 文件大小
     */
    private String size;
}
