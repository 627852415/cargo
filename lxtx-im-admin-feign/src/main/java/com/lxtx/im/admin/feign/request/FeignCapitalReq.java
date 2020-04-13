package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * 交易明细参数类
 *
 * @author CaiRH
 * @since 2018-09-27
 */
@Getter
@Setter
public class FeignCapitalReq extends BasePageReq{
    /**
     * 交易类型【1：转账，2：收款，3：好友转账】
     *          不传可查全部
     */
    private Integer type;

    /**
     * 游戏类型【1：牛牛，2：21点】
     */
    private Integer gameType;

    /**
     * 币种ID->币种名称
     */
    private String coinName;
    /**
     * 用户ID
     */
    private List<String> userIds;

    /**
     * 交易编号
     */
    private String transferNum;

    /**
     * 开始时间-交易时间
     */
    private Date createTime;
    /**
     * 结束时间-交易时间
     */
    private Date updateTime;
}
