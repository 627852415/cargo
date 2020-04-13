package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询币种手续费
 *
 * @author CaiRH
 * @Date 2018-09-05
 * @Description
 */
@Getter
@Setter
public class FeignCoinFeeReq {

    /**
     * 币种ID
     */
    private String coinId;

}
