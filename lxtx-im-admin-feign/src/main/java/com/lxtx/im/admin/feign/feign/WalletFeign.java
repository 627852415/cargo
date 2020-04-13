package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WalletFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignRedPacketCompatibleOldDataReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WalletFallbackFactory.class)
public interface WalletFeign {

    @PostMapping(value = "/v2/red/packet/compatibleOldData")
    BaseResult compatibleOldData(FeignRedPacketCompatibleOldDataReq req);
}
