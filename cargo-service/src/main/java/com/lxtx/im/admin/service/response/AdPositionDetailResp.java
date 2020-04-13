package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 广告位查询信息
 *
 * @author xufeifei
 * @since 2020-2-24
 */
@Getter
@Setter
public class AdPositionDetailResp {
    /**
     * 表主键ID
     */
    private String id;
    /**
     * 广告位名称
     */
    private String adPositionName;

    /**
     * 归属页面 1：发现页 2：app启动页
     */
    private Integer attributionPage;

    /**
     * 广告尺寸高度像素
     */
    private Integer height;

    /**
     * 广告尺寸宽度像素
     */
    private Integer width;

    /**
     * 展示方式 1：图片轮播 2：单图展示
     */
    private Integer displayMethod;

    /**
     * 广告素材备注，广告素材
     */
    private String materialNotes;

    /**
     * 轮播图数量
     */
    private Integer number;

    /**
     * 排期广告数
     */
    private Integer flightNumber;
}
