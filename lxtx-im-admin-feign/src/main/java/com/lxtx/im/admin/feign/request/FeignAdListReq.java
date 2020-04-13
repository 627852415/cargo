package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 广告列表
 * </p>
 *
 * @author map
 * @since 2020-02-24
 */
@Getter
@Setter
public class FeignAdListReq extends BasePageReq {

    /**
     * 广告名称
     */
    private String adName;

    /**
     * 广告位
     */
    private String pid;

    /**
     * 广告类型 1：url 2：跳转群主 3：跳转模块
     */
    private String adType;

    /**
     * 广告内容
     */
    private String adContent;

    /**
     * 广告投放国家简码
     * 不传查全部
     */
    private String countryCode;

    /**
     * 客户
     */
    private String customer;

    /**
     * 广告投放状态  1：排期中 2：进行中 3：已过期 4：暂停中
     * 不传查全部
     */
    private Integer status;

    /**
     * 广告投放开始时间
     */
    private String startTime;

    /**
     * 广告投放结束时间
     */
    private String endTime;
}
