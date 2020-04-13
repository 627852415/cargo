package com.lxtx.im.admin.service.request;

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
public class CommissionExtraWhitelistReq {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空")
    private String id;

    /**
     * 地区及国家名称
     */
    private String countryName;

}
