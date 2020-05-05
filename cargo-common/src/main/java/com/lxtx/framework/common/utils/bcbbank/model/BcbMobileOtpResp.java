package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;


@Data
public class BcbMobileOtpResp {

    private Integer status;
    private String request_id;
    private String error_text;

}
