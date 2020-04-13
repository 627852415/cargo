package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.UserCoinAssetFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyListDownloadReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinAssetDetailReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinAssetDiffReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinAssetListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 资金流水
 * @author: CXM
 * @create: 2018-08-31 09:59
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = UserCoinAssetFeignFallbackFactory.class)
public interface UserCoinAssetFeign {

    /**
     * 详情
     * @param detailReq
     * @return
     */
    @PostMapping(value = "/user/coin/asset/detail")
    BaseResult detail(FeignUserCoinAssetDetailReq detailReq);

    /**
     * 列表(带分页)
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/user/coin/asset/list/page")
    BaseResult listPage(FeignUserCoinAssetListReq req);


    /**
     * 列表
     *
     * @param downloadReq
     * @return
     */
    @PostMapping(value = "/user/coin/asset/list")
    BaseResult list(FeignPlatformWithdrawApplyListDownloadReq downloadReq);


    /**
     * 导出列表
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/user/coin/asset/download/list")
    BaseResult downloadList(FeignUserCoinAssetListReq listPageReq);


    /**
     * 生成资金流水差异报表
     *
     * @param
     * @return
     */
    @PostMapping("/user/coin/asset/diffReport")
    BaseResult generateFlowReport(FeignUserCoinAssetDiffReq listPageReq);

}
