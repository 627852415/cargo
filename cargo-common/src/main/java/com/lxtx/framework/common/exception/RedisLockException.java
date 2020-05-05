
package com.lxtx.framework.common.exception;

import com.lxtx.framework.common.constants.Constants;

/**
 * @since 2019-04-01
 */
@SuppressWarnings("serial")
public class RedisLockException extends LxtxException {

	public RedisLockException(String msg) {
		super(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, msg);
	}

	public RedisLockException(String code, String msg) {
		super(code, msg);
	}
}
