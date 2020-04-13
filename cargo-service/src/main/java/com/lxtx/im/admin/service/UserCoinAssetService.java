package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.UserCoinAssetDetailReq;
import com.lxtx.im.admin.service.request.UserCoinAssetDiffReq;
import com.lxtx.im.admin.service.request.UserCoinAssetListReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 资金流水
 * @author: CXM
 * @create: 2018-08-31 09:55
 **/
public interface UserCoinAssetService {

    /**
     * 列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(UserCoinAssetListReq req);

    /**
     * 详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(UserCoinAssetDetailReq req, Model model);

    /**
     * 文件导出
     *
     * @param response
     * @param req
     */
    void downloadList(HttpServletResponse response, UserCoinAssetListReq req);

    BaseResult generateDiffFlowReport(UserCoinAssetDiffReq req);
}
