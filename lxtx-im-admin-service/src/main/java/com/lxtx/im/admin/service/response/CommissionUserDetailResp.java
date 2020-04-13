package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 返佣信息查询
 *
 * @author xufeifei
 * @since 2020-3-7
 */
@Getter
@Setter
public class CommissionUserDetailResp {

    @ExcelField(name = "ID", orderBy = "1")
    private String id;
    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 用户返佣级别
     */
    private String levelId;

    /**
     * 币种名称
     */
    private String coinName;

    /**
     * 用户缴纳金额
     */
    private BigDecimal paymentAmount;

    /**
     * 状态【1：创建中，2：正常，3：禁用】
     */
    private Integer status;

    /**
     * 平台用户ID
     */
    @ExcelField(name = "平台用户ID", orderBy = "3")
    private String platformUserId;

    /**
     * 平台用户名称
     */
    @ExcelField(name = "用户名称", orderBy = "2")
    private String name;

    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 平台用户手机号
     */
    @ExcelField(name = "平台用户手机号", orderBy = "5")
    private String telephone;

    /**
     * 直属下级数量
     */
    @ExcelField(name = "直属下级数量", orderBy = "7")
    private Integer directLowerNumber;

    /**
     * 直属下级本月新增数
     */
    private Integer directLowerNewNumber;

    /**
     * 月度佣金
     */
    private BigDecimal amountOfMonth;

    /**
     * 手机区号
     */
    @ExcelField(name = "手机区号", orderBy = "4")
    private String phoneCode;

    /**
     * 国家
     */
    private String cnName;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 认证类型【0：身份证， 1：护照】
     */
    private Integer certificateType;

    /**
     * 级别名称
     */
    @ExcelField(name = "用户返佣级别", orderBy = "6")
    private String levelName;

    /**
     * 创建时间
     */
    @ExcelField(name = "创建时间", orderBy = "8")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 统计用户的月度返佣
     */
    private List<CommissionOrderStatisResp> commissionOrderStatisResps;
    
    /**
     * 用户升级记录列表
     */
    private List<AdminCommissionUserUpdateResp> adminCommissionUserUpdateListResp;

}
