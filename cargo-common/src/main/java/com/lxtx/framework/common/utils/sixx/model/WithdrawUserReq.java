package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Czh
 * Date: 2018/8/30 下午4:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawUserReq {

    private Integer userId;
    private String coin;
    private BigDecimal amount;
    private String transferNum;
    private String remark;
}