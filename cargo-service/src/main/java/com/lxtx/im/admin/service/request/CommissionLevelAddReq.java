package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
public class CommissionLevelAddReq {

    /**
     * 级别ID
     */
    private String id;
    /**
     * 业务类型  1：普通 2：伙伴
     *
     */
    @NotNull(message = "type不能为空")
    private Integer type;
    /**
     * 投入金额
     */
    @NotNull(message = "amount不能为空")
    private BigDecimal amount;
    /**
     * 级别名称
     */
    @NotBlank(message = "levelName不能为空")
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
    @NotNull(message = "ratio不能为空")
    private Float ratio;
    /**
     * 排序号
     */
    private Integer sort;


}
