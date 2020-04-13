package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  资金流水列表请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class FeignUserCoinAssetDiffReq extends BasePageReq {
    /**
     * 钱包用户ID
     */
    private String walletUserId;

    private String type;

}
