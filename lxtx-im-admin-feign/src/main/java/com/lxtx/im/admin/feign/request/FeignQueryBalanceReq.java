package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 查询余额
 * @author: tangdy
 * @create: 2018-08-22
 **/
@Setter
@Getter
public class FeignQueryBalanceReq {
    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 货币
     */
    private String currency;

    /**
     * 转换币种ID
     */
    private String toCoinId;

    /**
     * 是否请求钱包列表
     */
    private Boolean inside;

    /**
     * 币种ID
     */
    private String coinId;
    /**
     * 显示交易流水数量，默认5条
     */
    private Integer size;
}
