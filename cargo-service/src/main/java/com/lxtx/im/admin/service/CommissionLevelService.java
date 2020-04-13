package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CommissionLevelAddReq;
import com.lxtx.im.admin.service.request.CommissionLevelByIdReq;
import com.lxtx.im.admin.service.request.CommissionLevelListReq;

public interface CommissionLevelService {

    /**
     * 新增分佣级别
     * @param req
     * @return
     */
    BaseResult addLevel(CommissionLevelAddReq req);

    /**
     * 根据级别ID获取单条分佣级别
     * @param req
     * @return
     */
    BaseResult getLevel(CommissionLevelByIdReq req);

    /**
     * 分佣级别列
     * @param req
     * @return
     */
    BaseResult levelList(CommissionLevelListReq req);

    /**
     * 获取所有分佣列表
     * @param
     * @return
     */
    BaseResult levelAll();

}
