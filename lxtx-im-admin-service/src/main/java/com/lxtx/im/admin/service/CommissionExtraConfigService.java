package com.lxtx.im.admin.service;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CommissionExtraConfigAddReq;
import com.lxtx.im.admin.service.request.CommissionExtraConfigAddToStringReq;
import com.lxtx.im.admin.service.request.CommissionExtraConfigListReq;

import java.util.List;

/**
 * <p>
 * 额外分佣配置表 服务类
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
public interface CommissionExtraConfigService {

    /**
     * 额外分佣配置列表
     * @return
     */
    BaseResult selectExtraConfigList();

    /**
     * 新增、更新额外分佣配置
     * @param req
     * @return
     */
    BaseResult addExtraConfig(List<CommissionExtraConfigAddReq> req);
}
