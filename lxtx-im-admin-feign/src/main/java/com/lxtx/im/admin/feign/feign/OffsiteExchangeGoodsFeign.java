package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.OffsiteExchangeGoodsFeignFallBackFactory;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeCloseGoodsByAdminV5Req;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeGoodsDownloadReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeGoodsEditReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeGoodsPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 *  线下换汇商品api
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:38
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = OffsiteExchangeGoodsFeignFallBackFactory.class)
public interface OffsiteExchangeGoodsFeign {

    @PostMapping("/offsite/merchant/publish/download")
    BaseResult goodsDownload(FeignOffsiteExchangeGoodsDownloadReq req);

    @PostMapping("/offsite/merchant/publish/adminPage")
    BaseResult adminPage( FeignOffsiteExchangeGoodsPageReq req);

    @PostMapping("/offsite/merchant/publish/up")
    BaseResult up( FeignOffsiteExchangeGoodsEditReq req);

    @PostMapping("/offsite/merchant/publish/down")
    BaseResult down( FeignOffsiteExchangeGoodsEditReq req);

    @PostMapping("/offsite/merchant/publish/syncOldGoodsPay/v5")
    BaseResult syncOldGoodsPay();

    @PostMapping("/offsite/merchant/publish/close/goods/byadmin/v5")
    BaseResult closeGoodsByAdmin(FeignOffsiteExchangeCloseGoodsByAdminV5Req req);

    @PostMapping("/offsite/merchant/publish/deductTheFundsOfTheTotalAccount")
    BaseResult deductTheFundsOfTheTotalAccount();
}
