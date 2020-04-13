package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 手续费统计报表返回分页
 *
 * @author CaiRH
 * @since 2018-12-13
 **/
@Getter
@Setter
public class ChargeReportPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<ChargeReportDataResp> records;
}
