package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/2 15:23
 */
@Getter
@Setter
public class FeignBcbBankCardTypeSaveReq {
    private String id;
    private String coinId;
    private String legalCoinName;
    private Integer cardType;
    private String symbol;
    private String cardImgUrl;
    private String remarks;
}
