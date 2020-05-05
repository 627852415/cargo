package com.lxtx.framework.common.utils.bigfile;

import com.lxtx.framework.common.utils.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

/**
 * <p>
 * 大文件下载工具类 （支持断点）
 * </p>
 *
 * @author liboyan
 * @Date 2019-01-14 16:38
 * @Description
 */
@Slf4j
public class DownloadBigFileUtil {

    private static final String FILE_LENGTH = "File-Length";

    private static final int DEFAULT_BUFFER_SIZE = 5242880;

    private static final String RANDOMACCESSFILE_RW = "rw";

    private static final String ACCEPT_RANGES_VALUE = "bytes";

    private static final String ATTACHMENT = "attachment; filename=\"%s\"";

    /**
     * 功能描述: 大文件下载（支持断点下载）
     *
     * @param
     * @return
     * @author liboyan
     * @date 2019-01-15 10:39
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath) {
        // Get your file stream from wherever.
        log.info("下载路径:{}", filePath);
        File downloadFile = new File(filePath);
        int downloadSize = (int) downloadFile.length();
        response.setHeader(FILE_LENGTH, downloadSize + StringUtils.EMPTY);
        ServletContext context = request.getServletContext();
        // get MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (StringUtils.isBlank(mimeType)) {
            // set to binary type if MIME mapping not found
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // set content attributes for the response
        response.setContentType(mimeType);

        // set headers for the response
        String headerValue = String.format(ATTACHMENT, downloadFile.getName());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        // 解析断点续传相关信息
        response.setHeader(HttpHeaders.ACCEPT_RANGES, ACCEPT_RANGES_VALUE);

        long fromPos = 0, toPos = 0;
        if (request.getHeader(HttpHeaders.RANGE) == null) {
            response.setHeader(HttpHeaders.CONTENT_LENGTH, downloadSize + StringUtils.EMPTY);
        } else {
            // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader(HttpHeaders.RANGE);
            String bytes = range.replaceAll("bytes=", StringUtils.EMPTY);
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader(HttpHeaders.CONTENT_LENGTH, size + StringUtils.EMPTY);
            downloadSize = size;
        }
        // Copy the stream to the response's output stream.
        RandomAccessFile in = null;
        OutputStream out = null;
        try {
            in = new RandomAccessFile(downloadFile, RANDOMACCESSFILE_RW);
            // 设置下载起始位置
            if (fromPos > 0) {
                in.seek(fromPos);
            }
            // 缓冲区大小
            int bufLen = (downloadSize < DEFAULT_BUFFER_SIZE ? downloadSize : DEFAULT_BUFFER_SIZE);
            byte[] buffer = new byte[bufLen];
            int num;
            // 当前写到客户端的大小
            int count = 0;
            out = response.getOutputStream();
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                count += num;
                //处理最后一段，计算不满缓冲区的大小
                if (downloadSize - count < bufLen) {
                    bufLen = (downloadSize - count);
                    if (bufLen == 0) {
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            // 没有找到文件
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            log.error("数据被暂停或中断:{} {}", e.getMessage(), e);
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("数据被暂停或中断:{} {}", e.getMessage(), e);
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("数据被暂停或中断:{} {}", e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 功能描述: 大文件下载（支持断点续传，多线程）
     *
     * @param
     * @return
     * @author liboyan
     * @date 2019-01-15 10:39
     */
    public static BigFile downloadBase64(BigFile bigFile) {
        // 客户端已接收的字节数
        Long receiveSize = bigFile.getReceiveSize();

        // 分段大小
        Long segmentSize = bigFile.getSegmentSize();

        // 要下载的文件
        File downloadFile = new File(bigFile.getDownloadUrl());
        // 文件总字节数
        Long totalSize = downloadFile.length();

        // 负责读取数据
        RandomAccessFile raf = null;
        // 暂存容器
        byte[] buf = new byte[segmentSize.intValue()];
        try {
            raf = new RandomAccessFile(downloadFile, "r");
            if (receiveSize <= totalSize) {
                // 重指定位置开始下载
                raf.seek(receiveSize);
                int length = 0;
                length = raf.read(buf, 0, segmentSize.intValue());
                if (length < segmentSize.intValue()) {
                    byte[] copy = Arrays.copyOf(buf, length);
                    bigFile.setDownloadBase64(Base64.encodeBase64String(copy));
                } else {
                    bigFile.setDownloadBase64(Base64.encodeBase64String(buf));
                }

            }
            Double uploadProgress = NumberUtils.divide(Double.valueOf(bigFile.getReceiveSize()), Double.valueOf(totalSize), 4) * 100;
            bigFile.setProgress(uploadProgress.intValue());
            bigFile.setTotalSize(totalSize);
            bigFile.setReceiveSize(receiveSize + segmentSize);
            return bigFile;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
}
