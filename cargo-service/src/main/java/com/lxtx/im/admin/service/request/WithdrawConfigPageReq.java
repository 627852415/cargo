package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2019-06-25 15:15
 */
@Setter
@Getter
public class WithdrawConfigPageReq extends BasePageReq {

    private String coinName;
}