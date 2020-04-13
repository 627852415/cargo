package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;


/**
 * 保存币种参数类
 *
 * @author CaiRH
 * @since 2018-09-03
 */
@Getter
@Setter
public class CoinSaveReq {
    /**
     * 币种ID
     */
    private String id;
    /**
     * 币种名称
     */
    @NotBlank(message = "币种名称不能为空")
    private String coinName;
    /**
     * 美元汇率
     */
    private BigDecimal usdxExchange;
    /**
     * 手续费
     */
    private BigDecimal withdrawFee;
    /**
     * 币种图标地址
     */
    private String icoUrl;
    /**
     * 显示标记【default 0代表显示，1代表不显示】
     */
    private Boolean hideFlag;

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
    private BigDecimal toCny;
}
