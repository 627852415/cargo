package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @Description:    空投
* @Author:         CXM on 2018/8/31 10:33
*/
@Setter
@Getter
public class AirdropResp implements Serializable {

    private String id;
    /**
     * 币种id
     */
    private String coinId;
    /**
     * 币种名称
     */
    private String coinName;
    /**
     * 空投开始时间
     */
    private Date startTime;
    /**
     * 空投结束时间
     */
    private Date endTime;
    /**
     * 解锁时间
     */
    private Date unlockTime;
    /**
     * 空投总量
     */
    private BigDecimal amount;
    /**
     * 单位空投数量
     */
    private BigDecimal unitAmount;
    /**
     * 已空投人数
     */
    private Integer peoplesAmount;
    /**
     * 空投类型，1为注册空投；2为全站空投
     */
    private Integer type;
    /**
     * 是否結束空投
     */
    private Integer finishFlag;
}
