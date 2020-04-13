package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* @description:  币种手续费保存入参
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class CoinChargeSaveReq {
    /**
     * 主键
     */
    private String id;
    /**
     * 手续费类型（1、红包； 2、好友转账； 3、钱包转账）
     */
    private int chargeType;

    /**
     * 币种Id
     */
    @NotBlank(message = "币种不能为空")
    private String coinId;
    /**
     * 参数值
     */
    @NotNull (message = "值不能为空")
    private BigDecimal value;

    /**
     * 手续费方式（1、百分比 2、固定值）
     */
    private int type;
}
