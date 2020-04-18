package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OffsiteExchangeGoodsPageReq extends BasePageReq {

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
     * 开始时间
     */
    private String startTime;
    /**
     * 结束
     */
    private String endTime;

    /**
     * 上架状态  【0 上架  1下架 2 关闭】
     */
    private String pullOffFlag;

    /**
     * 电话号码
     */
    private String telephone;
}
