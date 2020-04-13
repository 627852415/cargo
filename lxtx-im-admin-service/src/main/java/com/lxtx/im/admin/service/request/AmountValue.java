package com.lxtx.im.admin.service.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/8 16:07
 */
@Data
public class AmountValue {
    private String coinName;
    private BigDecimal value;
}
