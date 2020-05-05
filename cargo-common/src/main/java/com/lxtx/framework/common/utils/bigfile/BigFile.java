package com.lxtx.framework.common.utils.bigfile;

import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 大文件信息
 * </p>
 *
 * @author liboyan
 * @Date 2019-01-11 14:41
 * @Description
 */
@Data
@ToString
public class BigFile {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 已接收文件大小
     */
    private Long receiveSize = 0L;
    /**
     * 文件总大小
     */
    private Long totalSize = 0L;

    /**
     * 分段大小
     */
    private Long segmentSize = 2048L;

    /**
     * 上传进度
     */
    private int progress = 0;
    /**
     * 下载地址
     * */
    private String downloadUrl;

    /**
     *  当次下载的内容
     * */
    private String downloadBase64;

}
