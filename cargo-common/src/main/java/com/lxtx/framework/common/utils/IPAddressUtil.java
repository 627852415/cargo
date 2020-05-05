package com.lxtx.framework.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 功能说明：IP地址工具
 *
 * @author zkj
 *
 */
@Slf4j
public class IPAddressUtil {

	private IPAddressUtil() {
	}

	/**
	 * 从发的request请求的头信息里获取客户端IP地址
	 * 
	 * @param request
	 * @return ip 客户端IP地址
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(",")) {
			return ip.split(",")[0];
		} else {
			return ip;
		}
	}

	/**
	 * 获取本机ip地址
	 *
	 * @return
	 */
	public static String getServerIpAddress() {
		String localIp = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			if (address != null) {
				localIp = address.getHostAddress();
			}
		} catch (UnknownHostException e) {
			log.error("error", e);
		}
		return localIp;
	}

	/**
	 * 判断是否是ipv6地址
	 * 
	 * @Description
	 * @param ip
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static Boolean isIPv6LiteralAddress(String ip) {
		return sun.net.util.IPAddressUtil.isIPv6LiteralAddress(ip);
	}

	/**
	 * 将 简写的IPv6 转换成 非简写的IPv6
	 * 
	 * @param fullIPv6 简写的IPv6
	 * @return 非简写的IPv6
	 */
	public static String parseAbbreviationToFullIPv6(String abbreviation) {
		String fullIPv6 = "";

		if ("::".equals(abbreviation)) {
			return "0000:0000:0000:0000:0000:0000:0000:0000";
		}

		String[] arr = new String[] { "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000" };

		if (abbreviation.startsWith("::")) {
			String[] temp = abbreviation.substring(2, abbreviation.length()).split(":");
			for (int i = 0; i < temp.length; i++) {
				String tempStr = "0000" + temp[i];
				arr[i + 8 - temp.length] = tempStr.substring(tempStr.length() - 4);
			}

		} else if (abbreviation.endsWith("::")) {
			String[] temp = abbreviation.substring(0, abbreviation.length() - 2).split(":");
			for (int i = 0; i < temp.length; i++) {
				String tempStr = "0000" + temp[i];
				arr[i] = tempStr.substring(tempStr.length() - 4);
			}

		} else if (abbreviation.contains("::")) {
			String[] tempArr = abbreviation.split("::");

			String[] temp0 = tempArr[0].split(":");
			for (int i = 0; i < temp0.length; i++) {
				String tempStr = "0000" + temp0[i];
				arr[i] = tempStr.substring(tempStr.length() - 4);
			}

			String[] temp1 = tempArr[1].split(":");
			for (int i = 0; i < temp1.length; i++) {
				String tempStr = "0000" + temp1[i];
				arr[i + 8 - temp1.length] = tempStr.substring(tempStr.length() - 4);
			}

		} else {
			String[] tempArr = abbreviation.split(":");

			for (int i = 0; i < tempArr.length; i++) {
				String tempStr = "0000" + tempArr[i];
				arr[i] = tempStr.substring(tempStr.length() - 4);
			}

		}

		fullIPv6 = StringUtils.join(arr, ":");

		return fullIPv6;

	}
	
	// IPV6地址的分段8段
	private static final int IPV6Length = 8;
	// IPV4地址分段4段
	private static final int IPV4Length = 4;
	// 一个IPV6分段占的长 16进制下
	private static final int IPV6ParmLength = 4;
	// 一个IPV4分段占的长度 16进制下
	private static final int IPV4ParmLength = 2;

	/**
	 * ipv6转ipv4 有损转换
	 * 
	 * 
	 * IPV6 为 128bit     16bit × 8
	 *
	 * 例： 192.168.0.118
	 * IPv4为十进制，所以转换成十六进制的IPv6就变成：
	 * 192.168.0.118 → 0000：0000：0000：0000：0000：0000：c0a8：0076
	 * c0a8:0076         0可省略变成       ::c0a8:0076 或 ::c0a8:0:76
	 * IPv6为十六进制，所以转换成十进制的IPv4就变成：
	 * c0=192  ：a8=168  ：0=00  ：76=118
	 * @Description
	 * @param ipv6
	 * @return
	 */
	public static String badIPv6ToIPv4(String ipv6) {
		if (!isIPv6LiteralAddress(ipv6)) {
			return ipv6;
		}
		StringBuilder ipv4SB = new StringBuilder();
		String fullIpv6 = parseAbbreviationToFullIPv6(ipv6);
		try {
			String[] hexs = fullIpv6.split(":");
			// 忽略标准未缩写的前六节内容
			String ipv4D1 = hexs[IPV6Length-2].substring(0,IPV4ParmLength);
			String ipv4D2 = hexs[IPV6Length-2].substring(IPV4ParmLength, IPV6ParmLength);
			String ipv4D3 = hexs[IPV6Length-1].substring(0,IPV4ParmLength);
			String ipv4D4 = hexs[IPV6Length-1].substring(IPV4ParmLength, IPV6ParmLength);
			ipv4SB.append(Integer.parseInt(ipv4D1, 16)).append(".");
			ipv4SB.append(Integer.parseInt(ipv4D2, 16)).append(".");
			ipv4SB.append(Integer.parseInt(ipv4D3, 16)).append(".");
			ipv4SB.append(Integer.parseInt(ipv4D4, 16));
		}catch (Exception e) {
			log.error("BadIPv6ToIPv4 error",e);
			ipv4SB = new StringBuilder("192.168.1.1");
		}
		log.info("ipv6==>{},ipv4==>{}", ipv6, ipv4SB);
		return ipv4SB.toString();
	}
}
