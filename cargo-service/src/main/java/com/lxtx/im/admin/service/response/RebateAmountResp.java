package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/12 10:50
 */
@Setter
@Getter
public class RebateAmountResp {

    private BigDecimal merchantRebateAmount;//  '商家返利金额',
    private BigDecimal platformRebateAmount;//  '平台净利润',
    /**
     * 邀请总返利
     */
    private BigDecimal totalInviteAmount;

    private List<String> coinIdList;
}
