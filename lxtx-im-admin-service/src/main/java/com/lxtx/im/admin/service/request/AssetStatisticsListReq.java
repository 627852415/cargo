package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *    统计列表请求参数
 　　* @author Lin hj
 　　* @redoDateTimes 2019/6/14 10:51
 */
@Setter
@Getter
public class AssetStatisticsListReq extends BasePageReq {
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