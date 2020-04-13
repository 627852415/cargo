package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/12 15:15
 */
@Getter
@Setter
public class StatisticsOrderReq {
    private Date startDate;

    private Date endDate;
}
