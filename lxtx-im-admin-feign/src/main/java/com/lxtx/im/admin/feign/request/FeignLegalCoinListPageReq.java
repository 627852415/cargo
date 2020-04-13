package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 法币管理列表请求参数
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Setter
@Getter
public class FeignLegalCoinListPageReq extends BasePageReq {

    /**
     * 法币名称
     */
    private String legalCoinName;


}
