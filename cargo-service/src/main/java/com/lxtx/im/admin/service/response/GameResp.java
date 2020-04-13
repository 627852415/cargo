package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏返回信息
 *
 * @author CaiRh
 * @since 2018-11-13
 */
@Getter
@Setter
public class GameResp implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 游戏类型
     */
    private Integer type;
    /**
     * 游戏类型名称
     */
    private String typeName;
    /**
     * 游戏名称
     */
    private String name;
    /**
     * 游戏图片
     */
    private String icon;
    /**
     * 游戏类型图标
     */
    private String image;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 相关文件图片服务主机地址
     */
    private String viewHost;

    /**
     * 是否显示【默认0：不显示，1：显示】
     */
    private boolean display;
}
