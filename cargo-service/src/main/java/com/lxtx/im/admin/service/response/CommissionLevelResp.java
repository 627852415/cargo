package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 分佣级别管理
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Setter
@Getter
public class CommissionLevelResp {

    /**
     * 级别ID
     */
    private String id;
    /**
     * 业务类型  1：普通 2：伙伴
     *
     */
    private Integer type;
    /**
     * 投入金额
     */
    private BigDecimal amount;
    /**
     * 级别名称
     */
    private String levelName;
    /**
     * 币种
     */
    private String coinId;
    /**
     * 币种名称
     */
    private String coinName;
    /**
     * 分成比例
     */
    private Float ratio;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private String createTime;

}
