package com.lxtx.framework.common.utils.qrcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * 内部类，设置二维码相关参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QrCodeModel {
	/**
	 * 正文
	 */
	private String contents;
	/**
	 * 二维码宽度
	 */
	private int width = 150;
	/**
	 * 二维码高度
	 */
	private int height = 150;
	/**
	 * 图片格式
	 */
	private String format = "png";
	/**
	 * 编码方式
	 */
	private String character_set = "utf-8";
	/**
	 * 字体大小
	 */
	private int fontSize = 12;
	/**
	 * logo
	 */
	private File logoFile;
	/**
	 * logo所占二维码比例
	 */
	private float logoRatio = 0.20f;
	/**
	 * 二维码下文字
	 */
	private String desc;
	private int whiteWidth;// 白边的宽度
	private int[] bottomStart;// 二维码最下边的开始坐标
	private int[] bottomEnd;// 二维码最下边的结束坐标
}
