package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author LiuLP on 2018/12/10.
 */
@Getter
@Setter
public class ChargeListPageInfoResp implements Serializable {

    /**
     * 第三方用户ID
     */
    private  String threeUserId;

    /**
     * 币种名称
     */
    @ExcelField(name = "用户名称", orderBy = "1")
    private String userName;

    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "2")
    private String coinName;


    /**
     * 内部手续费
     */
    @ExcelField(name = "内部手续费", orderBy = "1")
    private BigDecimal innerFee = BigDecimal.ZERO;

    /**
     * 6X手续费
     */
    @ExcelField(name = "6X手续费", orderBy = "3")
    private BigDecimal threeFee = BigDecimal.ZERO;

    /**
     * 总手续费
     */
    @ExcelField(name = "总手续费", orderBy = "4")
    private BigDecimal allFee = BigDecimal.ZERO;

    /**
     * 手续费类型
     */
    @ExcelField(name = "手续费类型", orderBy = "5")
    private String chargeType;

    /**
     * 手续费状态
     */
    private int status;

    /**
     * 手续费类型
     */
    @ExcelField(name = "手续费处理状态", orderBy = "6")
    private String feeStatus;

    /**
     * 更新时间
     */

    private Date updateTime;

    /**
     * 更新时间
     */
    @ExcelField(name = "交易时间", orderBy = "7")
    private String updateTimeStr;




}
