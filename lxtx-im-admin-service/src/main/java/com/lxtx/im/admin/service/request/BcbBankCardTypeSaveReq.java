package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/2 13:49
 */
@Getter
@Setter
public class BcbBankCardTypeSaveReq {

    private String id;
    private String coinId;
    private String legalCoinName;
    private Integer cardType;
    private String symbol;
    private String cardImgUrl;
    private String remarks;

}
