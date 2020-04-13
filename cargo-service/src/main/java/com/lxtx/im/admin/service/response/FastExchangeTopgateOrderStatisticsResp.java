package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.request.AmountValue;
import lombok.Data;
import java.util.List;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/10 11:21
 */
@Data
public class FastExchangeTopgateOrderStatisticsResp {
    /**
     * 实际支付的数量
     */
    private List<AmountValue> totalActualPayAmount;

    /**
     * 实际获得的数量（获得币种）
     */
    private List<AmountValue> totalActualGotAmount;


    /**
     * 总的手续费（获得币种）
     */
    private List<AmountValue> totalFee;

    /**
     * 内部手续费（获得币种）
     */
    private List<AmountValue> totalInnerFee;

    /**
     * tgpay手续费（获得币种）
     */
    private List<AmountValue> totalThirdFee;

    /**
     * 秘密商户入账（获得币种）
     */
    private List<AmountValue> totalImMerchantIn;

    /**
     * topgate商户入账（获得币种）
     */
    private List<AmountValue> totalTopgateMerchantIn;

    /**
     * 秘密商户出账(支付币种)
     */
    private List<AmountValue> totalImMerchantOut;

    /**
     * topgate商户出账（获得币种）
     */
    private List<AmountValue> totalTopgateMerchantOut;

    /**
     * 商户收益，单位：gotCoin（获得币种）
     */
    private List<AmountValue> totalProfit;
    /**
     * 成交订单数
     */
    private Integer successOrders;
}
