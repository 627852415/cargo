package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 币种名称集合
 * @author: CXM
 * @create: 2018-09-03 11:18
 **/
@Setter
@Getter
public class FeignSaveAirdropReq {

    private String id;

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

}
