package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class StatisticsCoinChargeListReq {

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 开始交易时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    /**
     * 结束交易时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;
}
