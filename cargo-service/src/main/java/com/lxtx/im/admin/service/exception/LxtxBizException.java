package com.lxtx.im.admin.service.exception;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.utils.LocaleUtils;

public class LxtxBizException {

	public static LxtxException newException(SysErrorCode sysErrorCode) {
		return LxtxException.newException(sysErrorCode.getCode(), LocaleUtils.getMessage(sysErrorCode.getMsg()));
	}

	public static LxtxException newException(String msg) {
		return LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE, msg);
	}

	public static LxtxException newExceptionWithLocale(String key) {
		return LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE, LocaleUtils.getMessage(key));
	}

	public static LxtxException newException(String code, String msg) {
		return LxtxException.newException(code, msg);
	}
}
