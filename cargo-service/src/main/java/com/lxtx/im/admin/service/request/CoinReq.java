package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;


/**
 * 获取币种参数类
 *
 * @author CaiRH
 * @since 2018-08-29
 */
@Getter
@Setter
public class CoinReq extends BasePageReq {
    /**
     * 币种名称
     */
    private String coinName;
}
