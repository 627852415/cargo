package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * 商家保证金新增保存参数封装
 *
 * @Author: liyunhua
 * @Date: 2019/4/22
 */
@Getter
@Setter
public class MerchantDepositSaveReq {

    private String id;

    /**
     * 保证金币种id
     */
    private String coinId;

    /**
     * 保证金金额
     */
    private BigDecimal deposit;

    /**
     * 信用分
     */
    private Integer creditScore;

}
