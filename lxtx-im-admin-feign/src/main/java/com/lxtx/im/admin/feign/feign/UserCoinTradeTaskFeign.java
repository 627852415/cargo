package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.UserCoinTradeTaskFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignUserCoinTradeTaskListPageReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinTradeTaskReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户资产交易任务表
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = UserCoinTradeTaskFallbackFactory.class)
public interface UserCoinTradeTaskFeign {

    /**
     * 用户资产交易任务列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/user/coin/trade/task/listPage")
    BaseResult listPage(FeignUserCoinTradeTaskListPageReq req);

    @PostMapping("/user/coin/trade/task/reprocessTask")
    BaseResult reprocessTask(FeignUserCoinTradeTaskReq feignReq);

}
