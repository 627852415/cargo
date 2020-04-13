package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Ppai
 * Date: 2019-03-12 14:45
 */
@Setter
@Getter
public class FeignPayBillCheckRecordIndexReq extends BasePageReq {
    private String userId;

    private Date start;

    private Date end;

    private Integer cycle;

    private boolean searchAll;
}
