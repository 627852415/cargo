package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Czh
 * Date: 2018/8/30 下午3:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinAssignAddressReq {

    private Integer userId;
    private String coin;
}