package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.FeignSalaryInDetailReq;
import com.lxtx.im.admin.feign.feign.TransferWalletFeign;
import com.lxtx.im.admin.feign.request.*;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * 
 * @Description
 * @author qing 
 * @date: 2019年11月21日 下午6:50:54
 */
@Component
@Slf4j
@Setter
public class TransferWalletFeignFallback implements TransferWalletFeign {
    private Throwable cause;

	@Override
	public BaseResult listPage(FeignTransferWalletReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult listTransferUserNames(FeignTransferUserReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult listScanPayPage(FeignScanPayListPageReq feignReq) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult scanPayDetail(FeignScanPayDetailReq feignReq) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult salaryInPageList(FeignSalaryInPageListReq feignReq) {
		log.error("feign", cause);
		return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult salaryOutPageList(@Valid FeignSalaryInPageListReq feignReq) {
		log.error("feign", cause);
		return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult salaryInDetail(FeignSalaryInDetailReq feignReq) {
		log.error("feign", cause);
		return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult salaryOutDetail(FeignSalaryInDetailReq feignReq) {
		log.error("feign", cause);
		return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult listCount(FeignTransferWalletReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult list(FeignTransferWalletReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult listTransferScanPayUserIds(FeignTransferScanPayWrapperReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}
	
}
