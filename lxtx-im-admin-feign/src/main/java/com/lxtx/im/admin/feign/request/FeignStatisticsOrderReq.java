package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/12 11:58
 */
@Getter
@Setter
public class FeignStatisticsOrderReq {

    private Date startDate;

    private Date endDate;

    private Boolean isAll;

}
