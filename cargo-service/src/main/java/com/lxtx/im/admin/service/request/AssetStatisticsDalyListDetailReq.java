package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 *    统计列表请求参数
 * @author Lin hj
 * @date 2019/6/14 10:51
 */
@Setter
@Getter
public class AssetStatisticsDalyListDetailReq extends BasePageReq {

    /**
     * 日期
     */
    private Long recordsDate;

    /**
     * 平台类型
     */
    private Integer platformType;

    /**
     * 币种ID
     */
    private String coinId;

}
