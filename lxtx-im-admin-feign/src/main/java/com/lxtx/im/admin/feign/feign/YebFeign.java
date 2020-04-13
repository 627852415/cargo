package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.YebFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = YebFeignFallbackFactory.class)
public interface YebFeign {

    /**
     * 余额宝设置代理上级关系
     * @return
     */
    @PostMapping(value = "/yeb/user/refer")
    BaseResult refer(FeignYebUserReferReq referReq);

    @PostMapping(value = "/admin/yeb/order/listPage")
    BaseResult listPage(FeignYebOrderListReq referReq);


    @PostMapping(value = "/admin/yeb/order/downloadList")
    BaseResult downloadList(FeignYebOrderDownloadReq referReq);

    @PostMapping(value = "//admin/yeb/order/detail")
    BaseResult detail(FeignYebOrderDetailReq req);

    @PostMapping(value = "/yeb/admin/asyn/earning")
    BaseResult doSyncYebUserEearning(FeignYebUserEarningSyncReq req);

}
