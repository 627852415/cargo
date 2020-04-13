package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.TransferFriendsFeign;
import com.lxtx.im.admin.feign.request.FeignTransferFriendsReq;
import com.lxtx.im.admin.feign.request.FeignTransferUserReq;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description
 * @author qing 
 * @date: 2019年11月21日 下午6:50:54
 */
@Component
@Slf4j
@Setter
public class TransferFriendsFeignFallback implements TransferFriendsFeign {
    private Throwable cause;

	@Override
	public BaseResult listPage(FeignTransferFriendsReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult listTransferUserNames(FeignTransferUserReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult listCount(FeignTransferFriendsReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}

	@Override
	public BaseResult list(FeignTransferFriendsReq req) {
		log.error("feign", cause);
	    return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
	}
	
}
