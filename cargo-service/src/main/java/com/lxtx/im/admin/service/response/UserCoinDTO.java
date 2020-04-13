package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class UserCoinDTO{

    @ExcelField(name = "ID", orderBy = "1")
    private String id;
    /**
     * 用户ID
     */
    @ExcelField(name = "用户ID", orderBy = "2")
    private String userId;

    @ExcelField(name = "用户昵称", orderBy = "3")
    private String userName;
    /**
     * 国际简码
     */
    @ExcelField(name = "国家简码", orderBy = "4")
    private String countryCode;
    /**
     * 币种名称
     */
    @ExcelField(name = "币种", orderBy = "5")
    private String coinName;

    /**
     * 币种主键ID
     */
    private String coinId;
    /**
     * 余额
     */
    @ExcelField(name = "余额", orderBy = "6")
    private BigDecimal leftAmount;
    /**
     * 冻结金额
     */
    @ExcelField(name = "冻结金额", orderBy = "7")
    private BigDecimal frozenAmount;
    /**
     * 锁定金额
     */
    @ExcelField(name = "锁定金额", orderBy = "8")
    private BigDecimal lockAmount;

    /**
     * 创建时间
     */
    @ExcelField(name = "创建时间", orderBy = "9")
    public Date createTime;
    /**
     * 更新时间
     */
    @ExcelField(name = "修改时间", orderBy = "10")
    public Date updateTime;
    /**
     * 6X 系统分配的钱包地址
     */
    private String assignAddr;
    /**
     * 绑定的钱包地址
     */
    private String boundAddr;

    /**
     * 是否显示于首页
     */
    private Boolean showIndexFlag;
}
