package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/3 16:04
 */
@Getter
@Setter
public class BcbBankCardSaveReq {
    private String id;
    private String cardNo;
    private Integer status;
    private String cardId;
    private String remarks;
}
