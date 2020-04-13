package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import org.springframework.ui.Model;

/**
 * 系统提现配置
 *
 * @author CaiRH
 * @since 2018-12-20
 **/
public interface PlatformWithdrawConfigService {

    /**
     * 系统提现配置列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(PlatformWithdrawConfigListReq req);

    /**
     * 系统提现配置新增或修改查看详情
     *
     * @param req
     * @param model
     */
    void info(PlatformWithdrawConfigSelectReq req, Model model);

    /**
     * 保存提现配置
     *
     * @param req
     * @return
     */
    BaseResult save(PlatformWithdrawConfigSaveReq req);

    /**
     * 删除提现配置
     *
     * @param req
     * @return
     */
    BaseResult delete(PlatformWithdrawConfigDeleteReq req);

    /**
     * 获取otc每日限额
     *
     * @param req
     * @return
     */
    BaseResult limitListPage(OtcDailyLimitListPageReq req);
}
