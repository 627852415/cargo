package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.QuartzInfoResp;
import com.lxtx.im.admin.service.response.QuartzListPageResp;

/**
 * @description: 任务管理
 * @author: CXM
 * @create: 2018-09-27 15:38
 */
public interface QuartzService {
    /**
     * 获取任务列表
     *
     * @param req
     * @return
     */
    QuartzListPageResp listPage(QuartzListPageReq req);

    /**
     * 删除任务
     *
     * @param req
     * @return
     */
    BaseResult delete(QuartzDeleteReq req);

    /**
     * 根据id获取任务信息
     *
     * @param req
     * @return
     */
    QuartzInfoResp info(QuartzInfoReq req);

    /**
     * 保存或更新任务
     *
     * @param req
     * @return
     */
    BaseResult saveOrUpdate(QuartzSaveReq req);

    /**
     * 运行任务
     *
     * @param req
     * @return
     */
    BaseResult run(QuartzRunReq req);

    /**
     * 运行任务
     *
     * @param req
     * @return
     */
    BaseResult stop(QuartzStopReq req);
}
