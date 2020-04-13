package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2019-03-12 14:45
 */
@Setter
@Getter
public class FeignBillListReq extends BasePageReq {

    /**
     * 账单ID
     */
    private String billId;
}