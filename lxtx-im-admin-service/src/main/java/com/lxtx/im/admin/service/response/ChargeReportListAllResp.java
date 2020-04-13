package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 手续费统计报表
 *
 * @author CaiRH
 * @since 2018-12-13
 **/
@Getter
@Setter
public class ChargeReportListAllResp {


    List<ChargeReportDataResp> records;
}
