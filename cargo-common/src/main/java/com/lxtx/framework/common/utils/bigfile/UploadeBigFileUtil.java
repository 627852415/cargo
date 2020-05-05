package com.lxtx.framework.common.utils.bigfile;

import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * <p>
 * 上传大文件工具类 （支持断点续传）
 * </p>
 *
 * @author liboyan
 * @Date 2019-01-11 14:27
 * @Description
 */
public class UploadeBigFileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadeBigFileUtil.class);

    private static final String TEMPFILE_EXT_NAME = ".temp";

    /**
     * 功能描述: 检查文件上一次读写的位置
     *
     * @param fileName
     * @return
     * @author liboyan
     * @date 2019-01-14 11:29
     */
    public static BigFile checkBigFile(String fileName, String dirName) {
        RandomAccessFile randomAccessFile = null;
        BigFile bigFile = null;
        try {
            StringBuffer saveFilePath = new StringBuffer();
            // String localFileSavePath = "/home/lxtx/files/im/";
            String localFileSavePath = PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH);
            if (StringUtils.isNotBlank(localFileSavePath)) {
                saveFilePath.append(localFileSavePath);
            }
            String faullTempFileName = saveFilePath.append(dirName).append(File.separator).append(DateUtils.getDate()).append(File.separator).append(fileName).append(TEMPFILE_EXT_NAME).toString();
            LOGGER.info("文件名:{}", faullTempFileName);
            File file = new File(faullTempFileName);
            randomAccessFile = new RandomAccessFile(file, "rw");

            //获得文件大小
            long size = 0;
            if (file.exists() && file.isFile()) {
                size = file.length();
            }
            bigFile = new BigFile();
            bigFile.setFileName(file.getName());
            bigFile.setReceiveSize(size);
            return bigFile;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 功能描述: 大文件分段接收写入（支持断点续传）
     *
     * @param fileName    文件名称要求不能重复（可使用uuid作为名称）
     * @param fileByte    单个文件内容
     * @param totalSize   文件总大小
     * @param receiveSize 已接收到文件大小
     * @return
     * @author liboyan
     * @date 2019-01-14 10:29
     */
    public static BigFile upload(String fileName, String dirName, byte[] fileByte, long totalSize, long receiveSize) {
        BigFile bigFile = null;
        RandomAccessFile randomAccessFile = null;
        Long segmentSize = Long.valueOf(fileByte.length);
        StringBuffer saveFilePath = new StringBuffer();
        //String localFileSavePath = "/home/lxtx/files/im/";
        String localFileSavePath = PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH);
        if (StringUtils.isNotBlank(localFileSavePath)) {
            saveFilePath.append(localFileSavePath);
        }
        String fuallFileName = saveFilePath.append(dirName).append(File.separator).append(DateUtils.getDate()).append(File.separator).append(fileName).toString();
        String faullTempFileName = fuallFileName.concat(TEMPFILE_EXT_NAME);
        try {
            randomAccessFile = new RandomAccessFile(faullTempFileName, "rw");
            if (receiveSize < totalSize) {
                bigFile = new BigFile();
                bigFile.setFileName(fileName);
                bigFile.setTotalSize(totalSize);
                bigFile.setReceiveSize(receiveSize + segmentSize);
                bigFile.setSegmentSize(segmentSize);
                Double uploadProgress = NumberUtils.divide(Double.valueOf(bigFile.getReceiveSize()), Double.valueOf(totalSize), 4) * 100;
                bigFile.setProgress(uploadProgress.intValue());
                randomAccessFile.seek(receiveSize);
                randomAccessFile.write(fileByte, 0, segmentSize.intValue());
            }

            if (bigFile.getReceiveSize() >= totalSize) {
                //文件重命名
                randomAccessFile.close();
                File file = new File(faullTempFileName);
                file.renameTo(new File(fuallFileName));
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return bigFile;

    }

    /**
     * 功能描述: 大文件分段接收写入（支持断点续传）
     *
     * @param fileName    文件名称要求不能重复（可使用uuid作为名称）
     * @param fileByte    单个文件base64加密过后内容
     * @param totalSize   文件总大小
     * @param receiveSize 已接收到文件大小
     * @return
     * @author liboyan
     * @date 2019-01-14 10:29
     */
    public static BigFile uploadBase64(String fileName, String dirName, byte[] fileByte, long totalSize, long receiveSize) {
        return upload(fileName, dirName, Base64.decodeBase64(fileByte), totalSize, receiveSize);
    }

    /**
     * 功能描述: 大文件分段读取发送（支持断点续传）
     *
     * @param path 文件绝对路径
     * @return
     * @author liboyan
     * @date 2019-01-14 10:27
     */
    public static BigFile sendFile(String path) {
        long start = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(path, "r");
            File file = new File(path);
            String fileName = file.getName();
            String dirName = "chat";
            byte[] buf = new byte[5 * 1025 * 1024];
            long totalSize = randomAccessFile.length();

            BigFile bigFile = null;
            long receiveSize = checkBigFile(fileName, dirName).getReceiveSize();
            if (receiveSize <= totalSize) {
                randomAccessFile.seek(receiveSize);
                int length = 0;
                while ((length = randomAccessFile.read(buf)) > 0) {
                    //如果有效字节数不为5*1025*1024，则说明文件已经读到尾了，不够填充满byteBuf了
                    if (length < buf.length) {
                        byte[] copy = Arrays.copyOf(buf, length);
                        bigFile = upload(fileName, dirName, copy, totalSize, receiveSize);
                    } else {
                        bigFile = upload(fileName, dirName, buf, totalSize, receiveSize);
                    }
                    if (bigFile != null) {
                        receiveSize = bigFile.getReceiveSize();
                    }
                    LOGGER.info("大文件上传完成 {}", bigFile);
                }
            }
            LOGGER.info("大文件上传完成耗时：{}", System.currentTimeMillis() - start);
            return bigFile;
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }
        return null;
    }

    /**
     * 功能描述: 大文件分段读取发送（支持断点续传）
     *
     * @param path 文件绝对路径
     * @return
     * @author liboyan
     * @date 2019-01-14 10:27
     */
    public static BigFile sendFileBase64(String path) {
        long start = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(path, "r");
            File file = new File(path);
            String fileName = file.getName();
            String dirName = "chat";
            byte[] buf = new byte[5 * 1025 * 1024];
            long totalSize = randomAccessFile.length();

            BigFile bigFile = null;
            long receiveSize = checkBigFile(fileName, dirName).getReceiveSize();
            if (receiveSize <= totalSize) {
                randomAccessFile.seek(receiveSize);
                int length = 0;
                while ((length = randomAccessFile.read(buf)) > 0) {
                    //如果有效字节数不为5*1025*1024，则说明文件已经读到尾了，不够填充满byteBuf了
                    if (length < buf.length) {
                        byte[] copy = Arrays.copyOf(buf, length);
                        bigFile = uploadBase64(fileName, dirName, Base64.encodeBase64(copy), totalSize, receiveSize);
                    } else {
                        bigFile = uploadBase64(fileName, dirName, Base64.encodeBase64(buf), totalSize, receiveSize);
                    }
                    if (bigFile != null) {
                        receiveSize = bigFile.getReceiveSize();
                    }
                    LOGGER.info("大文件上传完成 {}", bigFile);
                }
            }
            LOGGER.info("大文件上传完成耗时：{}", System.currentTimeMillis() - start);
            return bigFile;
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }
        return null;
    }

    public static void main(String[] args) {

        //String path = "E:\\开发工具\\CentOS-7-x86_64-DVD-1708.iso";
        // String path = "C:\\Users\\Administrator\\Desktop\\0.jpg";
        String path = "C:\\Users\\Administrator\\Desktop\\secureCRT.rar";

        // 分段断点续传
        //sendFile(path);

        // 分段断点续传(base64)
        sendFileBase64(path);
    }
}
