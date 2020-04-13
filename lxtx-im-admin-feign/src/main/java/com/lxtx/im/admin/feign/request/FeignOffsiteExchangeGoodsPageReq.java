package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:44
 * @Description
 */
@Getter
@Setter
public class FeignOffsiteExchangeGoodsPageReq extends BasePageReq {
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
     * 平台用户ID集合
     */
    private List<String> accountIdList;

    /**
     *是否柬埔寨演示账号
     */
    private boolean isShowAccount;
}
