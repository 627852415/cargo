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
public class CommissionExtraWhitelistAddReq {

    /**
     * 地区/国家名称
     */
    @NotBlank(message = "countryName不能为空")
    private String countryName;
    /**
     * 国际简码
     */
    @NotBlank(message = "countryCode不能为空")
    private String countryCode;
    /**
     * 手机区号
     */
    @NotBlank(message = "phoneCode不能为空")
    private String phoneCode;

}
