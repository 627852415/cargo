package com.lxtx.im.admin.feign.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class TopGateWithdrawPaywayRemoveReq {
    private String id;
}
