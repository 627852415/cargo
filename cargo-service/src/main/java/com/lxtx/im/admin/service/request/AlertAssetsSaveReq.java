package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/20 9:43
 */
@Getter
@Setter
public class AlertAssetsSaveReq {

    /**
     * 主键
     */
    private String id;
    /**
     * 1 资产频繁操作配置；2 平台资产报警配置
     */
    private Integer type;
    /**
     * 0禁用； 1启用
     */
    private Boolean enable;
    /**
     * 描述
     */
    private String desc;
    /**
     * 最大次数
     */
    private String maxTimes;
    /**
     * 报警时间，毫秒
     */
    private Integer maxDate;
    /**
     * 平台资产报警比率 e.g. 0.05
     */
    private BigDecimal maxDiffRate;
    /**
     * 流水表的子类型，EnumUserCoinAssetSubType
     */
    private Integer flowSubType;
    /**
     * 报警邮件，多个逗号隔开
     */
    private String email;
    /**
     * 报警电报，多个逗号隔开
     */
    private String telegram;
    /**
     * 报警电话，多个逗号隔开
     */
    private String telephone;
    /**
     * 备注
     */
    private String remarks;

    private Boolean isCheck;

}
