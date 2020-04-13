package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * OTC买卖比值报表
 *
 * @Author: liyunhua
 * @Date: 2019/3/7
 */
@Setter
@Getter
public class OtcOrderDayStatisticsListPageReq extends BasePageReq {

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
    private String ratioRange;

    /**
     * 金额类型
     */
    private String amountType;

    /**
     * 金额范围
     */
    private String amountRange;

    /**
     * 是否查询所有
     */
    private boolean isSearchAll;

}
