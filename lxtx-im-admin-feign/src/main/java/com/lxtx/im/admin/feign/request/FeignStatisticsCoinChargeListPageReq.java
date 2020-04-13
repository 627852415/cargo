package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class FeignStatisticsCoinChargeListPageReq extends BasePageReq {

    /**
     * 手续费类型（1、红包； 2、好友转账； 3、钱包转账， 4、牛牛游戏手续费）
     */
    private Integer chargeType;
    /**
     * 币种Id
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
     * 第三方用户ID（eg: IM）
     */
    private List<String> threeUserIdList;

    /**
     * 开始交易时间
     */
    private Date updateTime;
    /**
     * 结束交易时间
     */
    private Date updateEndTime;
}
