package com.lxtx.im.admin.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 2018/07/04
 *
 */

@SuppressWarnings("all")
public class FastJsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(FastJsonUtil.class);

	public static final <T> List<T> getList(String jsontext, String list_str, Class<T> clazz) {
		JSONObject jsonobj = JSON.parseObject(jsontext);
		if (jsonobj == null) {
			return null;
		}
		Object obj = jsonobj.get(list_str);
		if (obj == null) {
			return null;
		}
		// if(obj instanceof JSONObject){}
		if (obj instanceof JSONArray) {
			JSONArray jsonarr = (JSONArray) obj;
			List<T> list = new ArrayList<T>();
			for (int i = 0; i < jsonarr.size(); i++) {
				list.add(jsonarr.getObject(i, clazz));
			}
			return list;
		}
		return null;
	}

	
	public static final <T> T getObject(String jsontext, String obj_str, Class<T> clazz) {
		JSONObject jsonobj = JSON.parseObject(jsontext);
		if (jsonobj == null) {
			return null;
		}

		Object obj = jsonobj.get(obj_str);
		if (obj == null) {
			return null;
		}

		if (obj instanceof JSONObject) {
			return jsonobj.getObject(obj_str, clazz);
		} else {
			logger.info(obj.getClass() + "");
		}

		return null;
	}

	// 注：传入任意的jsontext,返回的T都不会为null,只是T的属性为null
	public static final <T> T getObject(String jsontext, Class<T> clazz) {
		T t = null;
		try {
			t = JSON.parseObject(jsontext, clazz);
		} catch (Exception e) {
			logger.error("json字符串转换失败！" + jsontext, e);
		}
		return t;
	}

	public static final String toJSONString(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}

	public static <T> List<T> getList(String jsonStr, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonStr, clazz);
		} catch (Exception e) {
			logger.error("json字符串转List失败！" + jsonStr, e);
		}
		return list;
	}

	public static List<Map<String, Object>> listKeyMaps(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
			});
		} catch (Exception e) {
			logger.error("json字符串转map失败", e);
		}
		return list;
	}

	public static Map<String, Object> json2Map(String jsonStr) {
		try {
			return JSON.parseObject(jsonStr, Map.class);
		} catch (Exception e) {
			logger.error("json字符串转换失败！" + jsonStr, e);
		}
		return null;
	}

}
