package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2019-06-25 15:15
 */
@Setter
@Getter
public class FeignWithdrawConfigPageReq extends BasePageReq {

    private String coinName;
}