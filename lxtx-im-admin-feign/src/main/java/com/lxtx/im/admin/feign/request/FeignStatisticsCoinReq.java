package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 币种资产统计
 *
 * @Author tangdy
 * @Date 2018-09-10
 */
@Getter
@Setter
public class FeignStatisticsCoinReq {

    /**
     * 平台类型
     */
    private Integer platformType;

    /**
     * 统计的具体日期
     */
    private Long redoDateTimes;
}
