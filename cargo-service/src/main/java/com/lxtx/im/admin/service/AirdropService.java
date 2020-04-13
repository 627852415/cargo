package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;

import javax.servlet.http.HttpSession;

/**
 * @description: 空投管理
 * @author: CXM
 * @create: 2018-08-31 09:55
 **/
public interface AirdropService {
    /**
     * 获取空投列表数据
     *
     * @param airdropReq
     * @param session
     * @return
     */
    BaseResult listPage(AirdropReq airdropReq, HttpSession session);

    /**
     * 根據id刪除空投
     * @param req
     * @return
     */
    BaseResult deleteById(DeleteAirdropReq req);

    /**
     * 根据id获取空投相关数据
     *
     * @param req
     * @return
     */
    BaseResult getAirdropData(AirdropToSavePageReq req);

    /**
     * 保存空投
     *
     * @param req
     * @return
     */
    BaseResult saveOrEdit(SaveAirdropReq req);

    /**
     * 空投详情
     *
     * @param req
     * @return
     */
    BaseResult detailAirdropList(AirdropDetailReq req);
}
