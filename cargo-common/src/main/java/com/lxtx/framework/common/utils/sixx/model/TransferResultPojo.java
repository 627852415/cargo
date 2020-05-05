package com.lxtx.framework.common.utils.sixx.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易详情
 * 2018/05/26   --api接口更新，新增属性值
 *
 * @author cctv
 * @date 2018/05/26
 */
@Data
public class TransferResultPojo {

    private String id;
    /**
     * 转账数量
     */
    private BigDecimal amount;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 状态，1：待处理，2：处理中，3：已提交， 4：成功， 5：失败
     */
    private Integer state;
    /**
     * 转账手续费
     */
    private BigDecimal fee;

    /**
     * 当type是商户充币、用户充币或托管平台手续费时，此字段是托管平台生成的唯一编号；当type是商户转出、商户提币、用户转出或用户提币时，此字段是业务平台提供的交易编号，业务平台内唯一编号 |
     */
    private String transferNum;

    private String remark;
    /**
     * 转账类型，1：商户充币，2：商户提币，3：商户转出，4：用户充币，5：用户提币，6：用户转出 , 7：托管平台手续费
     */
    private Integer type;

    private String phone;

    private String coin;

    private String fromAddr;

    private String toAddr;


    /**
     * 提币来源 1 api接口 2 商户管理系统
     */
    private String source;
    /**
     * 被归集的订单或者要归集的订单 根据collectType判断
     */
    private String collectTransferId;
    /**
     * 1 用户充币引发的归集 (包括 用户传出 商户转入) 2 OTC 充币引发的归集(包括 用户传出 商户转入 ) 3 闪兑引发的归集  (包括 用户传出 商户转入 collectType  = 4 商户自己充币
     */
    private Integer collectType;
}
