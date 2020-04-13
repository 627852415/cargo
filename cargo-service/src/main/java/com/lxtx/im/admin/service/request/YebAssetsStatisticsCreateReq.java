package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 新建余额宝资产每日统计表 入参封装
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@Getter
@Setter
public class YebAssetsStatisticsCreateReq {

    /**
     * 新建的日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;

}
