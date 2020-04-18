package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 交易明细参数类
 *
 * @author CaiRH
 * @since 2018-09-27
 */
@Getter
@Setter
public class CapitalExcelOutputReq {
    /**
     * 交易类型【1：转账，2：收款，3：好友转账，4：红包，5：游戏】
     * 不传可查全部
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
     * 用户ID->用户名称
     */
    private String userName;
    /**
     * 交易编号
     */
    private String transferNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
