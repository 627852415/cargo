package com.lxtx.framework.common.utils;

import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @Title: 用于处理数组的工具类
 * @Description: 用于处理数组的工具类
 * @author: zkj
 * @date: 2018-11-23
 *
 */
public class ArrayUtil {

	/**
	 * 用于找出2个数组的里的字符串 差集,  以arr1 为主集,如果arr2里没有,arr1里有, 那么会把arr2没有的字符串就会打印出来
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List minusString(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] firstArr = arr1;
		String[] secondArr = arr2;

		for (String str : firstArr) {
			if (!list.contains(str.trim())) {
				list.add(str.trim());
			}
		}
		for (String str : secondArr) {
			if (list.contains(str.trim())) {
				history.add(str.trim());
				list.remove(str);
			}
		}
		return list;
	}
}
