package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.UserCoinTradeTaskService;
import com.lxtx.im.admin.service.request.UserCoinTradeTaskListPageReq;
import com.lxtx.im.admin.service.request.UserCoinTradeTaskReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资产交易任务管理
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Controller
@RequestMapping("/user/coin/trade/task")
public class UserCoinTradeTaskController {

    @Autowired
    private UserCoinTradeTaskService userCoinTradeTaskService;

    /**
     * 用户资产交易任务列表首页
     */
    @SysLogAop("用户资产交易任务列表首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("user:coin:trade:task:index")
    public String tradeTaskIndex() {
        return "userCoin/trade-task-index";
    }

    /**
     * 用户资产交易任务列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/02/18
     */
    @SysLogAop("用户资产交易任务列表")
    @PostMapping(value = "/listPage")
    @RequiresPermissions("user:coin:trade:task:index")
    @ResponseBody
    public BaseResult listPage(UserCoinTradeTaskListPageReq req) {
        return userCoinTradeTaskService.listPage(req);
    }

    /**
     * 处理【处理失败】状态的数据
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/3/5
     */
    @SysLogAop(value = "处理【处理失败】状态的数据", monitor = true)
    @PostMapping(value = "/reprocessTask")
    @RequiresPermissions("user:coin:trade:task:index")
    @ResponseBody
    public BaseResult reprocessTask(@RequestBody UserCoinTradeTaskReq req) {
        return userCoinTradeTaskService.reprocessTask(req);
    }


}
