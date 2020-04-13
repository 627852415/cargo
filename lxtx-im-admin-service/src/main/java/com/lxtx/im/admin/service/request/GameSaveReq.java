package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 游戏保存
 *
 * @author CaiRH
 */
@Setter
@Getter
public class GameSaveReq {

    /**
     * 主键
     */
    private String id;
    /**
     * 游戏类型
     */
    @NotNull(message = "游戏类型不能为空")
    private Integer type;
    /**
     * 游戏名称
     */
    @NotBlank(message = "游戏名称不能为空")
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
