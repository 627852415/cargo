package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 余额宝资产统计列表分页 feign入参封装
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@Getter
@Setter
public class FeignYebAssetsStatisticsPageReq extends BasePageReq {

    /**
     * ID
     */
    private String id;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
