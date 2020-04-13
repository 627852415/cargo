package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 币种表
 *
 * @author CaiRh
 * @since 2018-08-14
 */
@Getter
@Setter
@TableName("t_wallet_coin")
public class Coin extends BaseModel {

    @TableId
    private String id;
    /**
     * 币种名称
     */
    private String coinName;
    /**
     * 美元汇率
     */
    private BigDecimal usdxExchange;
    /**
     * 每笔最小限额
     */
    private BigDecimal withdrawLeast;
    /**
     * 每天最大限额
     */
    private BigDecimal withdrawMaxPerday;
    /**
     * 手续费
     */
    private BigDecimal withdrawFee;
    /**
     * 币种图标地址
     */
    private String icoUrl;
    /**
     * 显示标记【default 0代表显示，1代表不显示】
     */
    private Boolean hideFlag;
    /**
     * 6x托管平台是否存在该币种标志【0不存在，1代表存在】
     */
    private Boolean sixFlag;

    /**
     * 排序加权
     */
    private Integer idx;

}
