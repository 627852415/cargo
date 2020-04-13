package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

 /**
  *    资产统计请求参数
 　　* @author Lin hj
 　　* @redoDateTimes 2019/6/14 11:11
 */
@Setter
@Getter
public class FeignAssetStatisticsListReq extends BasePageReq {
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
