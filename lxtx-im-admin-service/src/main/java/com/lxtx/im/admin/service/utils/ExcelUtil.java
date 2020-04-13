package com.lxtx.im.admin.service.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导出excel
 * 
 * @author tangdy
 */
public class ExcelUtil<T> {

	Class<T> clazz;

	public ExcelUtil(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 设置每一个sheet导出的数量
	 */
	private static final int PAGE_SIZE = 50000;

	private static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static <T> void exportExcel(HttpServletResponse response, List<T> datas, String fileName, String sheetName) {
		exportExcel(response, datas, fileName, sheetName, DEFAULT_TIME_PATTERN);
	}

	public static <T> byte[] exportByteExcel(List<T> datas, String fileName, String sheetName) {
		return exportByteExcel(datas, fileName, sheetName, DEFAULT_TIME_PATTERN);
	}

	/**
	 * 导出 byte 数组
	 *
	 * @param datas     导出的数据
	 * @param fileName  excel文件名称
	 * @param sheetName sheet名称
	 * @param pattern   时间格式化
	 */
	public static <T> byte[] exportByteExcel(List<T> datas, String fileName, String sheetName, String pattern) {
		if (CollectionUtils.isEmpty(datas)) {
			return null;
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 创建excel内容
			HSSFWorkbook workbook = createHSSFWorkbookAndTitle(datas, sheetName, pattern);

			// 弹出保存框方式
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				workbook.write(os);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return os.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 导出
	 *
	 * @param datas     导出的数据
	 * @param fileName  excel文件名称
	 * @param sheetName sheet名称
	 * @param pattern   时间格式化
	 */
	public static <T> void exportExcel(HttpServletResponse response, List<T> datas, String fileName, String sheetName,
			String pattern) {
		if (CollectionUtils.isEmpty(datas)) {
			return;
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 创建excel内容
			HSSFWorkbook workbook = createHSSFWorkbookAndTitle(datas, sheetName, pattern);

			// 弹出保存框方式
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				workbook.write(os);

			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xls");

			ServletOutputStream out = response.getOutputStream();

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 创建excel内容
	 * 
	 * @param datas
	 * @param sheetName
	 * @param pattern
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 */
	private static <T> HSSFWorkbook createHSSFWorkbookAndTitle(List<T> datas, String sheetName, String pattern)
			throws IllegalAccessException {
		int size = datas.size();
		int page = size / PAGE_SIZE + (size % PAGE_SIZE > 0 ? 1 : 0);
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		for (int j = 0; j < page; j++) {
			List<T> dataList = new ArrayList<T>();
			int toIndex = 0;
			if (j == page - 1) {
				toIndex = size;
			} else {
				toIndex = PAGE_SIZE * (j + 1);
			}
			dataList = datas.subList(PAGE_SIZE * j, toIndex);

			String indexStr = page > 1 ? (j + 1 + "") : "";

			HSSFSheet sheet = workbook.createSheet(sheetName + indexStr);
			// 创建第一行
			HSSFRow row = sheet.createRow(0);

			// 设置title
			Boolean isHasTitle = false;
			int headSize = 0;

			HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
			HSSFCellStyle style = getStyle(workbook);
			for (int i = 0; i < dataList.size(); i++) {
				HSSFRow rowBatch = sheet.createRow(i + 1);
				Object o = dataList.get(i);
				if (!isHasTitle) {
					headSize = setHeader(row, columnTopStyle, o);
					isHasTitle = true;
				}
				for (Field field : o.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(ExcelField.class)) {
						// 获取字段注解
						ExcelField columnConfig = field.getAnnotation(ExcelField.class);

						HSSFCell cell = rowBatch.createCell(Integer.valueOf(columnConfig.orderBy()) - 1);
						setCellValue(cell, field.get(o), pattern);
						cell.setCellStyle(style);
					}
				}

			}
			// 让列宽随着导出的列长自动适应
			setColumnWidth(sheet, headSize);
		}
		return workbook;
	}

	/**
	 * 设置header
	 *
	 * @param row
	 * @param columnTopStyle
	 * @param o
	 * @return
	 */
	private static int setHeader(HSSFRow row, HSSFCellStyle columnTopStyle, Object o) {
		int headSize = 1;
		for (Field field : o.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(ExcelField.class)) {
				// 获取字段名
				// String fieldNames = o.getClass().getSimpleName() + "." + field.getName();
				// 获取字段注解
				ExcelField columnConfig = field.getAnnotation(ExcelField.class);
				HSSFCell cell = row.createCell(Integer.valueOf(columnConfig.orderBy()) - 1);
				cell.setCellStyle(columnTopStyle);
				cell.setCellValue(columnConfig.name());
				headSize++;
			}
		}
		return headSize;
	}

	/**
	 * 设置列值
	 *
	 * @param cell
	 * @param value
	 * @param pattern
	 * @return
	 */
	private static void setCellValue(HSSFCell cell, Object value, String pattern) {
		String textValue = "";
		if (value instanceof Boolean) {
			/*
			 * boolean bValue = (Boolean) value; textValue = "男"; if (!bValue) { textValue =
			 * "女"; }
			 */
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			textValue = sdf.format(date);
		} else if (value instanceof byte[]) {

		} else if (value instanceof Integer) {
			textValue = value + "";
		} else if (value instanceof BigDecimal) {
			textValue = ((BigDecimal) value).stripTrailingZeros().toPlainString();
		} else {
			// 其它数据类型都当作字符串简单处理
			if (StringUtils.isNotBlank((String) value)) {
				textValue = value.toString();
			}
		}

		// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
		if (textValue != null) {
			Pattern p = TEXT_PATTERN;
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
				// 是数字当作double处理
				cell.setCellValue(Double.parseDouble(textValue));
			} else {
				HSSFRichTextString richString = new HSSFRichTextString(textValue);
				cell.setCellValue(richString);
			}
		}
	}

	private static Pattern TEXT_PATTERN = Pattern.compile("^//d+(//.//d+)?$");
	private static Pattern TEXT_CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

	/**
	 * 列宽随着导出的列长自动适应
	 *
	 * @param sheet
	 * @param headSize
	 */
	private static void setColumnWidth(HSSFSheet sheet, int headSize) {
		for (int colNum = 0; colNum < headSize; colNum++) {
			int columnWidth = sheet.getColumnWidth(colNum) / 256;
			String stringCellValue = "";
			for (int rowNum = 0; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
				HSSFRow currentRow;
				// 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(colNum) != null) {
					HSSFCell currentCell = currentRow.getCell(colNum);
					if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						stringCellValue = currentCell.getStringCellValue();
						if (StringUtils.isNotBlank(stringCellValue)) {
							Pattern p = TEXT_CHINESE_PATTERN;
							Matcher matcher = p.matcher(stringCellValue);
							int length = stringCellValue.getBytes().length;
							if (!matcher.matches()) {
								length = length * 2;
							}
							if (columnWidth < length) {
								columnWidth = length;
							}
						}
					}
				}
			}
			int maxWidth = 15000;
			if ((columnWidth * 256) > maxWidth) {
				sheet.setColumnWidth(colNum, maxWidth);
			} else {
				sheet.setColumnWidth(colNum, columnWidth * 256);
			}
		}
	}

	/**
	 * 得到实体类所有通过注解映射了数据表的字段
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Field> getMappedFiled(Class clazz, List<Field> fields) {
		if (fields == null) {
			fields = new ArrayList<Field>();
		}

		// 得到所有定义字段
		Field[] allFields = clazz.getDeclaredFields();
		// 得到所有field并存放到一个list中.
		for (Field field : allFields) {
			if (field.isAnnotationPresent(ExcelField.class)) {
				fields.add(field);
			}
		}
		if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
			getMappedFiled(clazz.getSuperclass(), fields);
		}

		return fields;
	}

	/**
	 * 列头单元格样式
	 * 
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return style;
	}

	/**
	 * 列数据信息单元格样式
	 *
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 12);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		return style;
	}

	/**
	 * 获取导出文件名
	 */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static String getExcelOutFileName(String fileNamePrefix, Date startTime, Date endTime) {
		if (startTime != null) {
			if (endTime != null) {
				fileNamePrefix = fileNamePrefix + dateFormat.format(startTime) + "至" + dateFormat.format(endTime);
			} else {
				fileNamePrefix = fileNamePrefix + dateFormat.format(startTime) + "至" + dateFormat.format(new Date());
			}
		} else {
			if (endTime != null) {
				fileNamePrefix = fileNamePrefix + "至" + dateFormat.format(endTime);
			} else {
				fileNamePrefix = fileNamePrefix + dateFormat.format(new Date());
			}
		}
		return fileNamePrefix;
	}

	/**
	 * 
	 * @Description 分段保存数据到临时目录
	 * @param <T>
	 * @param datas
	 * @param segmenteFile
	 * @return
	 */
	public static <T> boolean segmentedDatas(List<T> datas, File segmenteFile) {
		if (!segmenteFile.getParentFile().exists()) {
			segmenteFile.getParentFile().mkdirs();
		}
		try (FileOutputStream fos = new FileOutputStream(segmenteFile);) {
			fos.write(JSON.toJSONString(datas).getBytes());
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @Description 获取分段数据
	 * @param <T>
	 * @param classZ
	 * @param segmentedFile
	 * @return
	 */
	public static <T> List<T> getSegmentedDatas(Class<T> classZ, File segmentedFile) {
		try (FileInputStream fis = new FileInputStream(segmentedFile)) {
			byte[] buff = new byte[fis.available()];
			fis.read(buff);
			String jsonArryText = new String(buff);
			return JSON.parseArray(jsonArryText, classZ);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Description 追加工作表
	 * @param <T>
	 * @param excelFile
	 * @param datas
	 * @param sheetName
	 * @param pattern
	 * @return
	 */
	public static <T> boolean segmentedAddToExcel(File excelFile, List<T> datas, String sheetName, String pattern) {
		if (!excelFile.getParentFile().exists()) {
			excelFile.getParentFile().mkdirs();
		}
		File tmpFile = new File(excelFile.getParentFile().getAbsolutePath() + File.separatorChar + excelFile.getName()
				+ "_tmp_" + UUID.randomUUID().toString().replace("-", ""));
		try (FileOutputStream fos = new FileOutputStream(tmpFile);
				HSSFWorkbook workbook = excelFile.exists() ? new HSSFWorkbook(new FileInputStream(excelFile))
						: new HSSFWorkbook();) {
			HSSFSheet sheet = workbook.createSheet(
					sheetName + (workbook.getNumberOfSheets() == 0 ? "" : workbook.getNumberOfSheets() + 1));
			HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
			HSSFCellStyle style = getStyle(workbook);
			int headSize = 0;
			for (int i = 0; i < datas.size(); i++) {
				if (i == 0) {// 处理标题
					HSSFRow rowHeader = sheet.createRow(i);
					headSize = setHeader(rowHeader, columnTopStyle, datas.get(i));
				}
				HSSFRow rowBatch = sheet.createRow(i + 1);
				for (Field field : datas.get(i).getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(ExcelField.class)) {
						// 获取字段注解
						HSSFCell cell = rowBatch
								.createCell(Integer.valueOf(field.getAnnotation(ExcelField.class).orderBy()) - 1);
						setCellValue(cell, field.get(datas.get(i)), pattern);
						cell.setCellStyle(style);
					}
				}
				if (i == datas.size() - 1) {
					// 让列宽随着导出的列长自动适应
					setColumnWidth(sheet, headSize);
					// Excel默认打开工作表
					if (workbook.getNumberOfSheets() > 0) {
						workbook.setActiveSheet(0);
					}
					workbook.write(fos);
					fos.flush();
					workbook.close();
					fos.close();
				}
			}
			FileUtils.copyFile(tmpFile, excelFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			tmpFile.delete();
		}
	}

	/**
	 * 
	 * @Description 分段数据合并
	 * @param <T>
	 * @param classZ
	 * @param excelFile
	 * @param segmentedFiles
	 * @param sheetName
	 * @param pattern
	 * @return
	 */
	public static <T> boolean segmentedsAddToExcel(Class<T> classZ, File excelFile, List<File> segmentedFiles,
			String sheetName) {
		List<T> datas = new ArrayList<>();
		for (File file : segmentedFiles) {
			for (T data : getSegmentedDatas(classZ, file)) {
				datas.add(data);
				if (datas.size() == PAGE_SIZE) {
					// 达到一个工作表上限，写入工作表
					boolean wflg = segmentedAddToExcel(excelFile, datas, sheetName, DEFAULT_TIME_PATTERN);
					// 如果某一段处理失败，直接返回失败
					if (!wflg)
						return wflg;
					// 清空，预备下一个工作表的数据
					datas.clear();
				}
			}
		}
		// 处理最后一页不满足分工作表的数据并返回处理完成的状态
		return datas.size() > 0 ? segmentedAddToExcel(excelFile, datas, sheetName, DEFAULT_TIME_PATTERN) : true;
	}

	/**
	 * 
	 * @Description 删除分片文件夹下所有文件夹和文件
	 * @param segmentedsFiles
	 */
	public static void segmentedsFiles(File segmented) {
		if (segmented.exists()) {
			File files[] = segmented.listFiles();
			int len = files.length;
			for (int i = 0; i < len; i++) {
				if (files[i].isDirectory()) {
					segmentedsFiles(files[i]);
				} else {
					files[i].delete();
				}
			}
			segmented.delete();
		}
	}
}
