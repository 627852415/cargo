package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author xufeifei
 * Date: 2020-3-17
 */
@Setter
@Getter
public class CommissionOrderStatisResp {

    /**
     * 币种Id
     */
    private String coinId;

    /**
     * 币种名称
     */
    private String coinName;

    /**
     * 总金额
     */
    private BigDecimal amount;
}
