package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsDownReq;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsCreateReq;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsPageReq;
import com.lxtx.im.admin.service.request.YebUserEarningSyncReq;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 余额宝资产统计列表业务类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
public interface YebAssetsStatisticsService {

    BaseResult doSyncYebUserEearning(YebUserEarningSyncReq req);

    /**
     * 分页查询余额宝资产统计列表
     * @param req
     * @return
     */
    BaseResult page(YebAssetsStatisticsPageReq req);

    /**
     * 新建余额宝资产统计表
     * @param req
     * @param response
     * @return
     */
    BaseResult create(YebAssetsStatisticsCreateReq req, HttpServletResponse response);

    /**
     * 下载余额宝资产统计表
     * @param req
     * @param response
     */
    void down(YebAssetsStatisticsDownReq req, HttpServletResponse response);

}
