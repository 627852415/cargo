package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.UserCoinDailyDetailReq;
import com.lxtx.im.admin.service.request.UserCoinDailyListReq;
import com.lxtx.im.admin.service.request.UserCoinDailySnapshotRebuildReq;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CaiRH
 * @since 2019-05-31
 **/
public interface UserCoinDailyListService {

    /**
     * 列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(UserCoinDailyListReq req);


    void downData(HttpServletResponse response,String id);

    /**
     * 详情
     *
     * @param req
     * @return
     */
    BaseResult detail(UserCoinDailyDetailReq req);


    /**
     * 详情
     *
     * @param req
     * @return
     */
    void createDetailExcel(HttpServletResponse response, UserCoinDailyDetailReq req);

    BaseResult rebuildSnapshot(UserCoinDailySnapshotRebuildReq req);
}
