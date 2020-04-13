package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 快照列表请求参数
 *
 * @author CaiRH
 * @since 2019-05-31
 */
@Setter
@Getter
public class FeignUserCoinDailyListReq extends BasePageReq {
    /**
     * 快照ID
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
