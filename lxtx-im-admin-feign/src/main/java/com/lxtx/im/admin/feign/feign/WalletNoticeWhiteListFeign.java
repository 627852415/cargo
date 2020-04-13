package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WalletNoticeWhiteListFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignNoticeWhiteListDeleteReq;
import com.lxtx.im.admin.feign.request.FeignNoticeWhiteListListReq;
import com.lxtx.im.admin.feign.request.FeignNoticeWhiteListSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangdy
 * @Date 2018-08-029
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WalletNoticeWhiteListFeignFallbackFactory.class)
public interface WalletNoticeWhiteListFeign {

    @PostMapping(value = "/notice/whitelist/page/list")
    BaseResult listPage(FeignNoticeWhiteListListReq req);

    @PostMapping(value = "/notice/whitelist/save")
    BaseResult save(FeignNoticeWhiteListSaveReq req);

    @PostMapping(value = "/notice/whitelist/delete")
    BaseResult delete(FeignNoticeWhiteListDeleteReq req);


}
