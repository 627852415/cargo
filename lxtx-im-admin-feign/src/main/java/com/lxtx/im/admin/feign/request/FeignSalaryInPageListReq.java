package com.lxtx.im.admin.feign.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2019/12/13 15:48
 */
@Data
public class FeignSalaryInPageListReq extends BasePageReq{
    /**
     * 代发工资订单号
     */
    private String salaryOrderNo;
    /**
     * 钱包用户id集合
     */
    private List<String> userIds;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
