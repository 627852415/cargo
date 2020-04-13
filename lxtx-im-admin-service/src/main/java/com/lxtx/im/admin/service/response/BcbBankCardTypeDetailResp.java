package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/3 10:26
 */
@Getter
@Setter
public class BcbBankCardTypeDetailResp {
    private String id;
    private String coinId;
    private String legalCoinName;
    private Integer cardType;
    private String symbol;
    private String cardImgUrl;
    private String remarks;
}
