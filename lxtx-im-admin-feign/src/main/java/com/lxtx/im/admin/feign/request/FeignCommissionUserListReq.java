package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 返佣伙伴用户请求参数
 * @author xufeifei
 * @since 2020-3-7
 */
@Getter
@Setter
public class FeignCommissionUserListReq extends BasePageReq {

    /**
     * 分成比例
     */
    private BigDecimal ratio;

    /**
     * 用户返佣级别
     */
    private String levelId;

    /**
     * 平台用户ID
     */
    private String platformUserId;

    /**
     * 钱包用户ID
     */
    private String userId;
}
