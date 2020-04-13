package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 获取币种详情参数类
 *
 * @author CaiRH
 * @since 2018-08-29
 */
@Getter
@Setter
public class CoinDetailReq {
    /**
     * 币种ID
     */
    @NotBlank(message = "币种ID不能为空")
    private String coinId;
}
