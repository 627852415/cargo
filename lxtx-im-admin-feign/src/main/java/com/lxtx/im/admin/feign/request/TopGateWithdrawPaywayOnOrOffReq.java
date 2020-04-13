package com.lxtx.im.admin.feign.request;

import lombok.Data;

@Data
public class TopGateWithdrawPaywayOnOrOffReq {
    private String id;

    private Boolean enable;
}
