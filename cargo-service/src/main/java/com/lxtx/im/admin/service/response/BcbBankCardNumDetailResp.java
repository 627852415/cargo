package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/3 17:24
 */
@Getter
@Setter
public class BcbBankCardNumDetailResp {
    private String id;
    private String cardNo;
    private Integer status;
    private String cardId;
    private String remarks;
}
