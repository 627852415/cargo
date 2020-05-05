package com.lxtx.framework.common.interceptor;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.utils.LocaleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public BaseResult processException(Exception e) {
		log.error("服务调用异常", e);
		if (e instanceof MethodArgumentNotValidException) {
			List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
			if (!CollectionUtils.isEmpty(errors)) {

				String error = errors.get(0).getDefaultMessage();
				String localeError;
				try{
					//没有对应的key
					localeError = LocaleUtils.getMessage(error);
				}catch (Exception ex){
					localeError = error;
				}
				return BaseResult.error(Constants.SYSERROR_PARAM_ERROR_CODE, localeError);
			}
			// 返回自定义异常
		} else if (e instanceof LxtxException) {
			LxtxException lxtxException = (LxtxException) e;
			return BaseResult.error(lxtxException.getCode(), e.getMessage());
		} else if (e instanceof HttpRequestMethodNotSupportedException) {
			return BaseResult.error(Constants.SYSERROR_HTTPREQUEST_METHOD_CODE, e.getMessage());
		} else if (e instanceof HttpMessageNotReadableException) {
			return BaseResult.error(Constants.SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_CODE, getMessage("global.exception.param.invalid.error.msg"));
		} else if(e instanceof MissingServletRequestPartException){
			return BaseResult.error(Constants.SYSERROR_PARAM_ERROR_CODE, e.getMessage());
		} else if(e instanceof UnauthorizedException) {
			return BaseResult.error(Constants.SYSERROR_SYSTEM_UNAUTHORIZED_EXCEPTION_CODE, getMessage("global.exception.unauthorized.msg"));
		}

		return BaseResult.error(Constants.SYSERROR_SYSTEM_ERROR_CODE, getMessage("global.exception.system.error.msg"));
	}

	private String getMessage(String key) {
		return getMessage(key, null);
	}

	private String getMessage(String key, String... params) {
		String msg = "";
		try {
			msg = LocaleUtils.getMessage(key, params);
		} catch (Exception e) {
		}

		return msg;
	}

}
