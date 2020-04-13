package com.lxtx.im.admin.feign.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商家成交统计列表分页请求类
 * </p>
 *
 **/
@Getter
@Setter
public class FeignExchangeMerchantTransactionStatisticsListPageReq extends BasePageReq {

	/**
     * 用户Id集合
     */
    private List<String> account;

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
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isShowAccount;

}
