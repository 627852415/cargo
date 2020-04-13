package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 */
@Getter
@Setter
public class ExchangeGoodsListPageReq extends BasePageReq {

    /**
     * 用户Id
     */
    private String account;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 币对Id
     */
    private String waveRateId;

    /**
     * 统计时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDateTime;

    /**
     * 统计时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDateTime;


}
