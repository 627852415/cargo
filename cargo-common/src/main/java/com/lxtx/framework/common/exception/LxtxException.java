
package com.lxtx.framework.common.exception;

/**
 * @author liboyan
 * @Date 2018-08-03 10:12
 * @Description
 */
@SuppressWarnings("serial")
public class LxtxException extends RuntimeException {

	private String code;

	public LxtxException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public static LxtxException newException(String code, String msg) {
		return new LxtxException(code, msg);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
