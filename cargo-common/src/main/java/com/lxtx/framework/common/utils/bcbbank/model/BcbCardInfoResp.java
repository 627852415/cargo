package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

/**
 * 银行卡号信息
 */
@Data
public class BcbCardInfoResp {

    private BcbBaseResp status;
    private String barcode;
    private String barcode2;
    private String cardNum;
    private String cardState;
    private String currency;
    private String month;
    private String securityCode;
    private String year;
}
