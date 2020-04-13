package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.request.AmountValue;
import lombok.Data;

import java.util.List;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/8 14:43
 */
@Data
public class WithdrawTopgateOrderStatisticsResp {
    /**
     * Topgate的总订单金额 ,单位：元，两位小数,不能小于 1.0，具体金额上限和下限根据实际情况制定
     */
    private List<AmountValue> totalAmount;

    /**
     * Topgate的总实际支付金额,订单实际支付的金额
     */
    private List<AmountValue> totalActualAmount;

    /**
     * 总手续费
     */
    private List<AmountValue> totalFee;

    /**
     * 总内部手续费
     */
    private List<AmountValue> totalInnerFee;

    /**
     * Topgate的总手续费
     */
    private List<AmountValue> totalThirdFee;

    /**
     * 总秘密商户出账
     */
    private List<AmountValue> totalImMerchantOut;

    /**
     * 总topgate商户出账
     */
    private List<AmountValue> totalTopgateMerchantOut;

    /**
     * 成交订单数
     */
    private Integer successOrders;
}
