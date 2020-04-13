package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户资产交易任务表
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Setter
@Getter
public class UserCoinTradeTaskReq {

    /**
     * id
     */
    private String id;

    /**
     * 钱包用户id
     */
    private String userId;


}
