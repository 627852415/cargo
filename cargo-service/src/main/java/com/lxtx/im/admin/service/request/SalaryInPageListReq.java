package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2019/12/13 14:52
 */
@Data
public class SalaryInPageListReq extends BasePageReq {
    /**
     * 代发工资订单号
     */
    private String salaryOrderNo;
    /**
     * 钱包用户id
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 币种id
     */
    private String coinId;
    /**
     * 手机号
     */
    private String payAccount;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
