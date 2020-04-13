package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 广告位列表
 *
 * @author xufeifei
 */
@Getter
@Setter
public class FeignAdPositionListPageReq extends BasePageReq{

    /**
     * 广告位名称
     */
    private String adPositionName;

    /**
     * 归属页面 1：发现页 2：app启动页
     */
    private Integer attributionPage;

    /**
     * 展示方式 1：图片轮播 2：单图展示
     */
    private Integer displayMethod;
}
