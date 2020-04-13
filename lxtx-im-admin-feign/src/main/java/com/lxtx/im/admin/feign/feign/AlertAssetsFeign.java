package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AlertAssetsFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsDetailReq;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsReq;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/19 15:14
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = AlertAssetsFallbackFactory.class)
public interface AlertAssetsFeign {

    /**
     * 报警列表
     *
     * @param feignAlertAssetsReq
     * @return
     */
    @PostMapping(value = "/alert/assets/list/page")
    BaseResult listPage(FeignAlertAssetsReq feignAlertAssetsReq);

    /**
     * 保存报警
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/alert/assets/save")
    BaseResult save(FeignAlertAssetsSaveReq req);

    /**
     * 启用或禁用
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/alert/assets/update")
    BaseResult update(FeignAlertAssetsSaveReq req);

    /**
     * 根据ID获取详情
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/alert/assets/getById")
    BaseResult getById(FeignAlertAssetsDetailReq req);
}
