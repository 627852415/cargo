package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.QuartzService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.QuartzInfoResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 定时任务管理
 * @author: CXM
 * @create: 2018-09-27 14:41
 **/
@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    /**
     * 定时器主页跳转
     *
     * @return
     */
    @SysLogAop("定时器主页跳转")
    @GetMapping(value = "/index")
    @RequiresPermissions("quartz:index")
    public String toIndex() {
        return "quartz/quartz-index";
    }

    /**
     * 定时器新增页
     *
     * @return
     */
    @SysLogAop("定时器新增页")
    @GetMapping(value = "/add")
    @RequiresPermissions("quartz:index")
    public String add() {
        return "quartz/quartz-save";
    }

    /**
     * 任务列表
     *
     * @return
     */
    @SysLogAop("任务列表")
    @PostMapping(value = "/list")
    @RequiresPermissions("quartz:index")
    @ResponseBody
    public BaseResult listPage(@Valid @RequestBody QuartzListPageReq req) {
        return BaseResult.success(quartzService.listPage(req));
    }

    /**
     * 删除任务
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除任务", monitor = true)
    @PostMapping(value = "/delete")
    @RequiresPermissions("quartz:index")
    @ResponseBody
    public BaseResult delete(@Valid @RequestBody QuartzDeleteReq req) {
        return quartzService.delete(req);
    }

    /**
     * 根据id获取任务信息
     *
     * @param req
     * @return
     */
    @SysLogAop("获取任务信息")
    @GetMapping(value = "/info")
    @RequiresPermissions("quartz:index")
    public String info(QuartzInfoReq req, Model model) {
        QuartzInfoResp info = quartzService.info(req);
        model.addAttribute("task", info);
        return "quartz/quartz-save";
    }

    /**
     * 保存或更新字典
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "保存或更新字典", monitor = true)
    @PostMapping(value = "/save")
    @RequiresPermissions("quartz:index")
    @ResponseBody
    public BaseResult saveOrUpdate(@Valid @RequestBody QuartzSaveReq req) {
        return quartzService.saveOrUpdate(req);
    }

    /**
     * 运行任务
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "运行任务", monitor = true)
    @PostMapping(value = "/run")
    @RequiresPermissions("quartz:index")
    @ResponseBody
    public BaseResult run(@Valid @RequestBody QuartzRunReq req) {
        return quartzService.run(req);
    }

    /**
     * 停止任务
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "停止任务", monitor = true)
    @PostMapping(value = "/stop")
    @RequiresPermissions("quartz:index")
    @ResponseBody
    public BaseResult stop(@Valid @RequestBody QuartzStopReq req) {
        return quartzService.stop(req);
    }

}
