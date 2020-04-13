package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统提现配置列表请求参数
 *
 * @author CaiRH
 * @since 2018-12-20
 */
@Setter
@Getter
public class FeignPlatformWithdrawConfigListReq extends BasePageReq {

    /**
     * 币种名称
     */
    private String coinName;
}
