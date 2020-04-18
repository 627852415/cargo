package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * BCB银行卡号表
 */
@Getter
@Setter
public class BcbBankCardNumberPageReq extends BasePageReq implements Serializable {


    private String id;
    /**
     * 号码
     */
    private String cardNo;
    /**
     * 状态，是否使用【默认0：否，1：秘密初审使用中，2：已使用】
     */
    private Integer status;
}
