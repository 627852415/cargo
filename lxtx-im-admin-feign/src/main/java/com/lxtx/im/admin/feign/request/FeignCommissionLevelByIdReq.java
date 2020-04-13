package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分佣级别管理
 * </p>
 *
 * @author 
 * @since 2020-03-07
 */
@Setter
@Getter
public class FeignCommissionLevelByIdReq {

    /**
     * 级别ID
     */
    private String id;

}
