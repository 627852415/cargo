package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @description: 查询对账列表
 * @author: Ppai
 * @create: 2019-03-12 13:50
 **/
@Getter
@Setter
public class PayBillCheckRecordIndexReq extends BasePageReq {

    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;

    private Integer cycle;

    private boolean searchAll;
}
