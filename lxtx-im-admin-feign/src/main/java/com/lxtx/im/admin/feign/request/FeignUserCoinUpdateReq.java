package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * 更新用户资产币种参数类
 *
 * @author CaiRH
 * @since 2018-09-21
 */
@Getter
@Setter
public class FeignUserCoinUpdateReq {
    /**
     * 用户资产ID
     */
    private String userCoinId;
    /**
     * 更新键
     */
    private String key;
    /**
     * 更新键值
     */
    private BigDecimal value;

}
