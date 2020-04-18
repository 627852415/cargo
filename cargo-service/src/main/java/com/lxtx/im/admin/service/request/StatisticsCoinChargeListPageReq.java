package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description:
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class StatisticsCoinChargeListPageReq extends BasePageReq {

    /**
     * 手续费类型（1、红包； 2、好友转账； 3、钱包转账， 4、牛牛游戏手续费）
     */
    @NotNull(message = "手续费类型不能为空")
    private Integer chargeType;
    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 处理状态
     */
    private Integer status;

    /**
     * 开始交易时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    /**
     * 结束交易时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateEndTime;
}
