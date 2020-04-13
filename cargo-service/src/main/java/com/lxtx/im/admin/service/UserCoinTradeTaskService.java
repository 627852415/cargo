package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.UserCoinTradeTaskListPageReq;
import com.lxtx.im.admin.service.request.UserCoinTradeTaskReq;

/**
 * 用户资产交易任务管理
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
public interface UserCoinTradeTaskService {

    BaseResult listPage(UserCoinTradeTaskListPageReq req);

    BaseResult reprocessTask(UserCoinTradeTaskReq req);

}
