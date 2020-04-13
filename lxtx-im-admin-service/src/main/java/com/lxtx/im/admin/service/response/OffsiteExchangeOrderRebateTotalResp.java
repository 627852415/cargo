package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Husky
 * @create 2019-05-24
 */
@Getter
@Setter
public class OffsiteExchangeOrderRebateTotalResp implements Serializable {

    private static final long serialVersionUID = -1014744234720482233L;

    /**
     * 平台利润
     */
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal platformRebateTotal;
    
    /**
     * 订单数量
     */
    private Integer orderTotal;
}

