package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @description:  列表请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class FeignStickerListReq extends BasePageReq {
    /**
     * 表情包名称
     */
    private String name;

    /**
     * 表情包状态，0正常，1下架
     */
    private Integer status;

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
