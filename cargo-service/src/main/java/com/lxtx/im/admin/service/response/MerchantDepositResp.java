package com.lxtx.im.admin.service.response;


import com.lxtx.im.admin.dao.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
@Getter
@Setter
public class MerchantDepositResp extends BaseModel {

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
     * 保证金（带币种单位）
     */
    private String depositDesc;

    /**
     * 信用分
     */
    private Integer creditScore;

}
