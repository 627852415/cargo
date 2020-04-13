package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CommissionExtraWhitelistAddReq;
import com.lxtx.im.admin.service.request.CommissionExtraWhitelistListReq;
import com.lxtx.im.admin.service.request.CommissionExtraWhitelistReq;
import com.lxtx.im.admin.service.request.CommissionWhitelistReq;

/**
 * <p>
 * 额外分佣白名单表 服务类
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
public interface CommissionExtraWhitelistService {
    /**
     * 获取白名单国家列表
     * @param req
     * @return
     */
    BaseResult selectWhitelist(CommissionWhitelistReq req);

    /**
     * 新增、更新额外分佣白名单
     * @param req
     * @return
     */
    BaseResult addExtraWhitelist(CommissionExtraWhitelistAddReq req);

    /**
     * 移除额外分佣白名单
     * @param req
     * @return
     */
    BaseResult delExtraWhitelist(CommissionExtraWhitelistReq req);

    /**
     * 额外分佣白名单列表
     * @param req
     * @return
     */
    BaseResult selectExtraWhitelistPage(CommissionExtraWhitelistListReq req);

    /**
     * 地区分佣比例编辑列表
     * @param req
     * @return
     */
    BaseResult selectExtraConfigList(CommissionExtraWhitelistReq req);
}
