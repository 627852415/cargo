package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 广告位保存
 *
 * @author xufeifei
 */
@Setter
@Getter
public class FeignAdPositionSaveReq {

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
