package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Czh
 * Date: 2018/8/30 下午4:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindMerchantAddressReq {

    private String coin;
    private String addr;
}