package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;

/**
 * @author Czh
 * Date: 2018/10/19 下午1:42
 */
public interface RefreshScopeService {

    /**
     * 刷新对应应用配置
     *
     * @param serviceName
     * @return
     */
    BaseResult refreshScope(String serviceName);

    /**
     * 获取应用服务名称列表
     *
     * @return
     */
    BaseResult getServiceList();
}
