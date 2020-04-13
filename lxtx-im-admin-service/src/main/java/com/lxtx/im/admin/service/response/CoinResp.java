package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 币种返回信息
 *
 * @author CaiRh
 * @since 2018-08-29
 */
@Getter
@Setter
public class CoinResp implements Serializable {

    private String id;
    /**
     * 币种名称
     */
    private String coinName;
    /**
     * 币种中文别名
     */
    private String aliasName;
    /**
     * 美元汇率
     */
    private String usdxExchange;
    /**
     * 每笔最小限额
     */
    private String withdrawLeast;
    /**
     * 每天最大限额
     */
    private String withdrawMaxPerday;
    /**
     * 手续费
     */
    private String withdrawFee;
    /**
     * 币种图标地址
     */
    private String icoUrl;
    /**
     * 显示标记【default 0代表显示，1代表不显示】
     */
    private Boolean hideFlag;
    /**
     * 6x托管平台是否存在该币种标志【0不存在，1代表存在】
     */
    private Boolean sixFlag;

    /**
     * 排序加权
     */
    private Integer idx;

    /**
     * 自定义汇率（默认通过定时器向托管平台同步汇率）【0否，1是】
     */
    private Boolean customizedRate;
    /**
     * 人民币
     */
    private String toCny;
}
