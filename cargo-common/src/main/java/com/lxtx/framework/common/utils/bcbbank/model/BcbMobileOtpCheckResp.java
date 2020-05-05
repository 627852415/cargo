package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

/**
 * @Author Husky
 * @create 2019-05-21
 */
@Data
public class BcbMobileOtpCheckResp {
    private Integer status;
    private String request_id;
    private String event_id;
    private String price;
    private String currency;


}
