package com.lxtx.im.admin.service.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lxtx.im.admin.service.utils.CustomJsonDateDeserializer;
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
public class SaveAirdropReq {

    private String id;

    /**
     * 币种id
     */
    private String coinName;
    /**
     * 空投开始时间
     */
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date startTime;
    /**
     * 空投结束时间
     */
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date endTime;
    /**
     * 解锁时间
     */
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
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

}
