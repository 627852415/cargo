package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OTC买卖比值报表
 *
 * @Author: liyunhua
 * @Date: 2019/3/7
 */
@Setter
@Getter
public class FeignOtcOrderDayStatisticsListPageReq extends BasePageReq {

    /**
     * 开始交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 结束交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 比值类型
     */
    private String ratioType;

    /**
     * 比值范围
     */
    private BigDecimal ratioMin;

    /**
     * 比值范围
     */
    private BigDecimal ratioMax;

    /**
     * 金额类型
     */
    private String amountType;

    /**
     * 金额范围
     */
    private BigDecimal amountMin;

    /**
     * 金额范围
     */
    private BigDecimal amountMax;

    /**
     * IM用户account集合
     */
    private List<String> userIds;

    /**
     * 是否查询所有
     */
    private boolean isSearchAll;
}
