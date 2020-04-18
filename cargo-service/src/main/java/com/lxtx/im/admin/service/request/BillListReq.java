package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2019-03-12 14:09
 */
@Getter
@Setter
public class BillListReq extends BasePageReq {

    /**
     * 账单ID
     */
    private String billId;
}