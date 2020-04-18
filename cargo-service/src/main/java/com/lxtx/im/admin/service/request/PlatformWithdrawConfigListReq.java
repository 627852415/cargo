package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统提现配置列表数据
 *
 * @author CaiRH
 */
@Setter
@Getter
public class PlatformWithdrawConfigListReq extends BasePageReq {

    /**
     * 币种名称
     */
    private String coinName;
}
