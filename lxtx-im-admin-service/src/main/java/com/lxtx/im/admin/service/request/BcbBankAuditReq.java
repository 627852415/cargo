package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BcbBankAuditReq implements Serializable {

    /**
     * 申请卡ID
     */
    private String id;

    /**
     * 是否通过
     */
    private Boolean isPass;

    /**
     * 审核不通过备注
     */
    private String remark;

}
