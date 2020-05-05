package com.lxtx.framework.common.utils.yeb.model;

import lombok.Data;

import java.io.Serializable;

/**
 * author lin hj on 2019/3/29
 *
 * principle：表示存在余额宝中的本金；
 * interest：表示存在余额宝中的收益；
 * chain：表示在链上的资金余额，有时候第三方业务也可能会转入一部分资金，但是并没有存入余额宝，导致链上的账户也存在余额。
 */
@Data
public class YebBalanceRes implements Serializable {


    /**
     * 表示存在余额宝中的本金；
     */
    private String principle;
    /**
     * 表示存在余额宝中的收益；
     */
    private String interest;
    /**
     * 表示在链上的资金余额，有时候第三方业务也可能会转入一部分资金，但是并没有存入余额宝，导致链上的账户也存在余额。
     */
    private String chain;



}
