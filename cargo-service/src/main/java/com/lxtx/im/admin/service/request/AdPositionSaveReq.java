package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @description:  请求参数
 * @author:   xufeifei
 * @create:   2020-2-24
 */
@Setter
@Getter
public class AdPositionSaveReq {

    private String id;

    /**
     * 广告位名称
     */
    @NotBlank(message = "广告位名称不能为空")
    private String adPositionName;

    /**
     * 归属页面 1：发现页 2：app启动页
     */
    @NotNull(message = "归属页面不能为空")
    private Integer attributionPage;

    /**
     * 广告尺寸高度像素
     */
    @NotNull(message = "广告尺寸height不能为空")
    private Integer height;

    /**
     * 广告尺寸宽度像素
     */
    @NotNull(message = "广告尺寸width不能为空")
    private Integer width;

    /**
     * 展示方式 1：图片轮播 2：单图展示
     */
    @NotNull(message = "展示方式不能为空")
    private Integer displayMethod;

    /**
     * 广告素材备注，广告素材
     */
    @NotNull(message = "广告素材备注不能为空")
    private String materialNotes;

    /**
     * 轮播图数量
     */
    @NotNull(message = "轮播图数量不能为空")
    private Integer number;

    /**
     * 排期广告数
     */
    private Integer flightNumber;
}
