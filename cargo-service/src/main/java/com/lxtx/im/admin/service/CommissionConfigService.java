package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CommissionConfigAddReq;
import com.lxtx.im.admin.service.request.CommissionConfigListReq;

import java.util.List;

public interface CommissionConfigService {

    /**
     * 新增分佣设置
     * @param req
     * @return
     */
    BaseResult addConfig(List<CommissionConfigAddReq> req);

    /**
     * 分佣设置列表
     * @return
     */
    BaseResult configList(CommissionConfigListReq req);

}
