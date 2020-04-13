package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeGoodsFeign;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeCloseGoodsByAdminV5Req;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeGoodsDownloadReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeGoodsEditReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeGoodsPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:46
 * @Description
 */
@Component
@Slf4j
@Setter
public class OffsiteExchangeGoodsFeignFallBack implements OffsiteExchangeGoodsFeign {
    private Throwable cause;


    @Override
    public BaseResult goodsDownload(FeignOffsiteExchangeGoodsDownloadReq req) {
        log.error("feign 下载线下换汇商品列表失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult adminPage(FeignOffsiteExchangeGoodsPageReq req) {
        log.error("feign 获取线下换汇商品分页失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult up(FeignOffsiteExchangeGoodsEditReq req) {
        log.error("feign 线下换汇商品上架失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult down(FeignOffsiteExchangeGoodsEditReq req) {
        log.error("feign 线下换汇商品下架失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult syncOldGoodsPay() {
        log.error("feign 补旧数据的商品的付款方式", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult closeGoodsByAdmin(FeignOffsiteExchangeCloseGoodsByAdminV5Req req) {
        log.error("feign 线下换汇关闭商家商品失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }


	@Override
	public BaseResult deductTheFundsOfTheTotalAccount() {
		log.error("feign 修复挂单无保证金，买家取消订单，补扣总账号的资金", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}
}
