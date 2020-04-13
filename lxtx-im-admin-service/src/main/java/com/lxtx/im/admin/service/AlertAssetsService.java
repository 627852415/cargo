package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.AlertAssetsDetailReq;
import com.lxtx.im.admin.service.request.AlertAssetsReq;
import com.lxtx.im.admin.service.request.AlertAssetsSaveReq;

/**
 * @Description: 资产报警管理
 * @author: ZhangZhongWu
 * @date: 2019/6/19 15:03
 */
public interface AlertAssetsService {

    /**
     * 报警列表
     *
     * @return
     */
    BaseResult listPage(AlertAssetsReq req);

    /**
     * 保存新增或编辑
     *
     * @param req
     * @return
     */
    BaseResult save(AlertAssetsSaveReq req);

    /**
     * 启用或禁用
     *
     * @param req
     * @return
     */
    BaseResult update(AlertAssetsSaveReq req);

    /**
     * 根据ID获取对象
     *
     * @param req
     * @return
     */
    BaseResult getById(AlertAssetsDetailReq req);
}


