package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 平台提款配置详情
 *
 * @author CaiRh
 * @since 2018-12-20
 */
@Setter
@Getter
public class PlatformWithdrawConfigInfoResp {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 币种ID
     */
    private String coinId;
    /**
     * 币种名称
     */
    private String coinName;

    /**
     * 有效时间内（单位s秒）
     */
    private Integer validTime;

    /**
     * 提款限额-最小金额
     */
    private String minMoney;
    /**
     * 提款限额-最大金额
     */
    private String maxMoney;

    /**
     * 手续费参数值
     */
    private String feeValue;
    /**
     * 手续费方式（1、百分比 2、固定值）
     */
    private Integer feeType;


    /**
     * 是否显示【0：默认不显示；1：显示】
     */
    private Boolean display;
    /**
     * 排序权重【从大到小】
     */
    private Integer idx;

}
