package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/3 16:20
 */
@Data
public class FeignBcbBankCardSaveReq {
    private String id;
    private String cardNo;
    private Integer status;
    private String cardId;
    private String remarks;
}
