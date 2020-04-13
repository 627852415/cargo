package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 删除币种参数类
 *
 * @author CaiRH
 * @since 2018-09-03
 */
@Getter
@Setter
public class CoinDeleteReq {
    /**
     * 币种ID
     */
    @NotBlank(message = "币种ID不能为空")
    private String coinId;
}
