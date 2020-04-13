package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 更新用户资产币种参数类
 *
 * @author CaiRH
 * @since 2018-09-21
 */
@Getter
@Setter
public class UserCoinUpdateReq {
    /**
     * 用户资产ID
     */
    @NotBlank(message = "更新用户资产不能为空")
    private String userCoinId;
    /**
     * 更新键
     */
    @NotBlank(message = "更新键不能为空")
    private String key;
    /**
     * 更新键值
     */
    @NotNull(message = "更新内容不能为空")
    private BigDecimal value;

}
