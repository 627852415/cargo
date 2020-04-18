package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询币种手续费
 *
 * @author CaiRH
 * @Date 2018-09-05
 * @Description
 */
@Getter
@Setter
public class CoinFeeReq {

    /**
     * 币种ID
     */
    @NotBlank(message = "币种ID不能为空")
    private String coinId;

}
