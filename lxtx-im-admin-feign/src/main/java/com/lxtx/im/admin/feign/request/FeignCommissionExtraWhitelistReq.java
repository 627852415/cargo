package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 额外分佣白名单表
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Getter
@Setter
public class FeignCommissionExtraWhitelistReq {

    /**
     * ID
     */
    private String id;
    /**
     * 配置类型  1：白名单配置  2：通用配置
     */
    private Integer type;

}
