package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Czh
 * Date: 2018/8/30 下午4:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BindUserAddressReq {

    private Integer userId;
    private String coin;
    private String addr;
}