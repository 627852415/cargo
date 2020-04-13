package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.AssetStatisticsDalyListDetailReq;
import com.lxtx.im.admin.service.request.AssetStatisticsListExportReq;
import com.lxtx.im.admin.service.request.AssetStatisticsListReq;

import javax.servlet.http.HttpServletResponse;

 /**
  *   资产统计 列表
 　　* @author Lin hj
 　　* @redoDateTimes 2019/6/14 11:07
 */
public interface AssetStatisticsDailyListService {

    /**
     * 列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(AssetStatisticsListReq req);


    void downData(HttpServletResponse response, String id);

    /**
     * 详情
     *
     * @param req
     * @return
     */
    BaseResult detail(AssetStatisticsDalyListDetailReq req);


    /**
     * 详情
     *
     * @param req
     * @return
     */
    void createDetailExcel(HttpServletResponse response, AssetStatisticsListExportReq req);

}
