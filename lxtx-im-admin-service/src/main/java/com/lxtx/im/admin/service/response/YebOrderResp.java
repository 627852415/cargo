package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-11-25 14:32
 * @Description
 */
@Data
public class YebOrderResp {
    @ExcelField(name = "ID", orderBy = "1")
    private String id;

    /**
     * 第三方生成的订单编号
     */
    @ExcelField(name = "资金托管订单号", orderBy = "2")
    private String thirdPartyOrderNo;


    /**
     * 6X提币订单号
     */

    @ExcelField(name = "6X订单号", orderBy = "3")
    private String sixxOrderNo;

    /**
     * 付款钱包用户ID
     */
    @ExcelField(name = "钱包用户ID", orderBy = "4")
    private String userId;

    /**
     *  平台用户ID
     */
    private String platformUserId;

    @ExcelField(name = "用户昵称", orderBy = "5")
    private String userName;
    /**
     * 国际简码
     */
    @ExcelField(name = "国家简码", orderBy = "6")
    private String countryCode;

    /**
     * 电话号码
     */
    @ExcelField(name = "电话号码", orderBy = "7")
    private String telephone;


    private Integer type;

    @ExcelField(name = "类型", orderBy = "8")
    private String typeName;


    private Integer status;

    @ExcelField(name = "状态", orderBy = "9")
    private String statusName;

    /**
     * 币种
     */
    @ExcelField(name = "币种", orderBy = "10")
    private String coinName;

    /**
     * 交易数量
     */
    @ExcelField(name = "金额", orderBy = "11")
    private BigDecimal amount;

    /**
     * 手续费
     */
    @ExcelField(name = "手续费", orderBy = "12")
    private BigDecimal fee;
    @ExcelField(name = "交易时间", orderBy = "13")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date createTime;
    @ExcelField(name = "更新时间", orderBy = "14")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date updateTime;

    @ExcelField(name = "备注", orderBy = "15")
    public String remarks;


}
