package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-25 15:48
 * @Description
 */
@Getter
@Setter
public class FeignOffsiteExchangeGoodsEditReq {
    /**
     * userId
     */

    private String userId;

    /**
     * merchantId
     */

    private String merchantId;

    /**
     * 编号
     */
    private String goodsId;

}
