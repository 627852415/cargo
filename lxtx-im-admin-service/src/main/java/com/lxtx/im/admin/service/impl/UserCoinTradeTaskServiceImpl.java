package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.UserCoinTradeTaskFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.FeignUserCoinTradeTaskListPageReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinTradeTaskReq;
import com.lxtx.im.admin.service.UserCoinTradeTaskService;
import com.lxtx.im.admin.service.request.UserCoinTradeTaskListPageReq;
import com.lxtx.im.admin.service.request.UserCoinTradeTaskReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户资产交易任务管理
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Service
public class UserCoinTradeTaskServiceImpl implements UserCoinTradeTaskService {

    @Autowired
    private UserCoinTradeTaskFeign userCoinTradeTaskFeign;

    @Autowired
    private UserFeign userFeign;


    @Override
    public BaseResult listPage(UserCoinTradeTaskListPageReq req) {
        //平台用户查询条件
        String account = req.getAccount();
        String name = req.getName();
        String telephone = req.getTelephone();
        //钱包查询条件
        String userId = req.getUserId();

        FeignUserCoinTradeTaskListPageReq feignReq = new FeignUserCoinTradeTaskListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return userCoinTradeTaskFeign.listPage(feignReq);
    }

    @Override
    public BaseResult reprocessTask(UserCoinTradeTaskReq req) {
        FeignUserCoinTradeTaskReq feignReq = new FeignUserCoinTradeTaskReq();
        feignReq.setId(req.getId());
        return userCoinTradeTaskFeign.reprocessTask(feignReq);
    }


}
