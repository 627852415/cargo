
package com.lxtx.framework.common.utils;

import java.util.UUID;

/**
 * UUID 工具类
 *
 * @author keriezhang
 * @date 2016/10/31
 */

public class UUIDTools {

	/**
	 *
	 * 功能描述: 获取uuid
	 *
	 * @auther: liboyan
	 * @date: 2018-06-15 09:18
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
