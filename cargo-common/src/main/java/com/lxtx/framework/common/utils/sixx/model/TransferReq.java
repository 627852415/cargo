package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Czh
 * Date: 2018/8/23 下午12:14
 */
@Data
public class TransferReq {

    private Integer userId;
    private String coin;
    private BigDecimal amount;
    private String transferNum;
    private String remark;
}