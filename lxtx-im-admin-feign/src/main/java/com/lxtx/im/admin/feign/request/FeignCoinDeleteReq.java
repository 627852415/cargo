package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 删除币种参数类
 *
 * @author CaiRH
 * @since 2018-09-03
 */
@Getter
@Setter
public class FeignCoinDeleteReq {
    /**
     * 币种ID
     */
    private String coinId;
}
