package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户换汇统计分页请求类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@Getter
@Setter
public class FeignExchangeUserStatisticsListPageReq extends FeignBasePageReq{

    /**
     * 用户Id集合
     */
    private List<String> account;

    /**
     * 币对Id
     */
    private String waveRateId;

    /**
     * 统计时间
     */
    private Date startDateTime;

    /**
     * 统计时间
     */
    private Date endDateTime;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isShowAccount;

}
