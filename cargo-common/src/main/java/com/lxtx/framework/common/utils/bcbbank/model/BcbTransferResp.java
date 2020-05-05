package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Lin hj
 * @title: BcbBaseResp
 * @projectName git_work
 * @description: TODO
 * @date 2019/4/2315:04
 */
@Data
public class BcbTransferResp {



    private BcbBaseResp status;

    private String recipient;

    private Transaction transaction;

    private CustomerSummary customerSummary;

    @Getter
    @Setter
    class Transaction{
        private String transactionNum;
        private BigDecimal total;
        private BigDecimal fee;
        private BigDecimal amount;
        private String summary;
        private String currency;
        private BigDecimal exchangeFac;
    }

    @Getter
    @Setter
    class CustomerSummary{
        private String CustomerToken;
        private String CustomerName;
    }


}
