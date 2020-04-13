package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.LegalCoinFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignLegalCoinDelReq;
import com.lxtx.im.admin.feign.request.FeignLegalCoinListPageReq;
import com.lxtx.im.admin.feign.request.FeignLegalCoinSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 法币管理
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = LegalCoinFallbackFactory.class)
public interface LegalCoinFeign {

    /**
     * 法币管理列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/admin/legal/coin/list/page")
    BaseResult listPage(FeignLegalCoinListPageReq req);

    @PostMapping("/admin/legal/coin/save")
    BaseResult save(FeignLegalCoinSaveReq feignReq);

    @PostMapping("/admin/legal/coin/del")
    BaseResult del(FeignLegalCoinDelReq feignReq);

    @PostMapping("/admin/legal/coin/detail")
    BaseResult detail(FeignLegalCoinSaveReq feignReq);

    @PostMapping("/admin/legal/coin/list")
    BaseResult list();
}
