package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 根据钱包地址查询钱包用户ID
 *
 * @author xufeifei
 */
@Getter
@Setter
public class FeignQueryWalletUserByAddrReq {

    private String assignAddr;
}
