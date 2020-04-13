package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户资产交易任务列表请求参数
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Setter
@Getter
public class FeignUserCoinTradeTaskListPageReq extends BasePageReq {

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 主键id
     */
    private String id;

    /**
     * 钱包用户id
     */
    private String userId;

    /**
     * 状态
     * */
    private Integer status;


}
