package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:42
 * @Description
 */
@Setter
@Getter
public class FeignOffsiteExchangeGoodsDownloadReq {
    /**
     * 编号
     */
    private String goodsId;

    /**
     * 支付状态 1:未付款 2:已完成 3:已取消
     */
    private Integer status;
    /**
     * 商家ID
     */
    private String merchantId;
    /**
     * 商家联系方式
     */
    private String fullTelephone;

    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束
     */
    private String endTime;
    /**
     * 最大行数
     */
    private Integer size;
}
