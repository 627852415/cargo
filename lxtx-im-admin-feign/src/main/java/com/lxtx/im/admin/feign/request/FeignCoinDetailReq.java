package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 获取币种详情参数类
 *
 * @author CaiRH
 * @since 2018-09-01
 */
@Getter
@Setter
public class FeignCoinDetailReq {
    /**
     * 币种ID
     */
    private String coinId;
}
