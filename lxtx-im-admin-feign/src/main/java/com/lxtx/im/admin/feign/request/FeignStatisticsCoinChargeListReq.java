package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class FeignStatisticsCoinChargeListReq{

    /**
     * 币种Id
     */
    private String coinId;
    /**
     * 交易时间
     */
    private Date createTime;
    /**
     * 结束交易时间
     */
    private Date endTime;
}
