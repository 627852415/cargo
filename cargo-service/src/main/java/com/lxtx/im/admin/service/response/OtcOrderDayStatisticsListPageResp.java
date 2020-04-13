package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Czh
 * Date: 2019-03-11 15:27
 */
@Getter
@Setter
public class OtcOrderDayStatisticsListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<OtcOrderDayStatisticsResp> records;
}
