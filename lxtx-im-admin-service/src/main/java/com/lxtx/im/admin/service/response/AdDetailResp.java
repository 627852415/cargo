package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 广告查询信息
 *
 * @author xufeifei
 * @since 2020-2-24
 */
@Getter
@Setter
public class AdDetailResp {
    /**
     * 广告ID,主键
     */
    private String id;
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
     * 广告素材地址
     */
    private String adUrl;
    /**
     * 投放地区类型 1：全部地区 2：其他地区
     */
    private Integer areaType;
    /**
     * 广告投放国家简码,多个国家逗号分隔
     */
    private String countryCode;
    /**
     * 投放国家名称,多个国家逗号分隔
     */
    private String countryName;
    /**
     * 客户
     */
    private String customer;
    /**
     * 广告投放状态  1：排期中 2：进行中 3：已过期 4：暂停中
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
