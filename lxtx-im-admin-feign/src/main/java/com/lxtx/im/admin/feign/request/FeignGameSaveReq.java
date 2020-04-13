package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 游戏保存
 *
 * @author CaiRH
 */
@Setter
@Getter
public class FeignGameSaveReq {

    /**
     * 主键
     */
    private String id;
    /**
     * 游戏类型
     */
    private Integer type;
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
     * 是否显示【默认0：不显示，1：显示】
     */
    private boolean display;
}
