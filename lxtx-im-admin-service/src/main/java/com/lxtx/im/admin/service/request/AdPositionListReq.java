package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:  列表请求参数
 * @author:   xufeifei
 * @create:   2020-2-24
 */
@Setter
@Getter
public class AdPositionListReq extends BasePageReq {

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
