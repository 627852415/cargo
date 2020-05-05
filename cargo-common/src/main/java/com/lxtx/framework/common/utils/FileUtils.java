package com.lxtx.framework.common.utils;


import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 服务器文件上传
 *
 * @author zkj
 * @date 2018/9/13
 */
@Slf4j
public class FileUtils {

    /**
     * 图片格式
     */
    public static final String PICTURE_SUFFIX = "jpg|JPG|png|PNG|jpeg|JPEG|tiff|TIFF";

    /**
     * 缩略图前缀
     */
    public static final String THUMB_PREFIX = "thumb_";

    /**
     * 大图前缀
     */
    public static final String BIG_PREFIX = "big_";


    /**
     * 分隔符
     */
    public static final String SEPARATOR = ".";
    /**
     * GIF
     */

    public static final String IMG_GIF = "GIF";


    /**
     * base64 文件上传
     *
     * @param fileByte    文件byte[]
     * @param fileName    文件名(必须用UUID去-生成)
     * @param fileExtName 文件后缀名
     * @param dirName     文件夹名字
     * @return 图片url
     */
    public String uploadFile( final byte[] fileByte, String fileName, String fileExtName, String dirName, Boolean createBigPicture) {
        long startTimeMillis = System.currentTimeMillis();

        //当天日期
        String dateFile = DateUtils.getDate();

        //文件夹名+当天日期 (例:usericon/20180912)
        String dirDateFile = dirName.concat(File.separator).concat(dateFile);

        //保存的文件夹 (例:/home/lxtx/files/im/usericon/20180912)
        String localFileSavePath = PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH);
        String saveFile = localFileSavePath.concat(dirDateFile);
        // 判断文件扩展名是否为空
        String fullFileName;
        if (StringUtils.isBlank(fileExtName)){
            fullFileName = fileName;
        }else{
            fullFileName = fileName.concat(SEPARATOR).concat(fileExtName);
        }

        String filePath = saveFile.concat(File.separator).concat(fullFileName);
        File file = new File(saveFile);
        if (!file.exists()) {
            file.mkdirs();
        }

        //图片，生成缩略图
        String thumbPath = saveFile.concat(File.separator).concat(THUMB_PREFIX).concat(fullFileName);

        // gif 图片处理
        if(IMG_GIF.equalsIgnoreCase(fileExtName)){
            // 保存原图
            writeFile(new File(filePath), fileByte);

            // 保存缩略原图
            writeFile(new File(thumbPath), fileByte);

        }else if (StringUtils.isNotBlank(fileExtName) && PICTURE_SUFFIX.contains(fileExtName)) {
            // "jpg|JPG|png|PNG|jpeg|JPEG|tiff|TIFF
            // 保存原图
            writeFile(new File(filePath), fileByte);

            // 大图生成
            String bigPath = saveFile.concat(File.separator).concat(BIG_PREFIX).concat(fullFileName);
            compressImage(filePath, bigPath, fileByte, 0.25f);

            // 缩略图生成
            compressImage(filePath, thumbPath, fileByte, 0.15f);

        } else {
            // 其他类型文件
            writeFile(new File(filePath), fileByte);
        }

        long endTimeMillis = System.currentTimeMillis();
        log.info("****保存文件用时：{}ms", endTimeMillis - startTimeMillis);

        // 返回图片路径
        StringBuffer respFilePath = new StringBuffer(PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_VIEW_PATH));
        respFilePath.append(File.separator).append(dirDateFile).append(File.separator).append(fullFileName);
        return respFilePath.toString();
    }

    /**
     * 文件删除
     *
     * @param filePath 文件路径(返回给客户端显示的路径,例: /file/chatfiles/20180913/64dcd17ef57347e094c5c766fc15d88c.png)
     */
    public void deleteFile(String filePath) {
        try {
            String localFileSavePath = PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH);
            String localFileViewPath = PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_VIEW_PATH);
            filePath = filePath.replace(localFileViewPath.concat(File.separator), localFileSavePath);
            File file = new File(filePath);
            if (file.exists()) {
                String fileName = file.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(SEPARATOR) + 1);
                //删除缩略图
                if (PICTURE_SUFFIX.contains(suffix)) {
                    //获取缩略图路径
                    String thumbUrl = file.getParent().concat(File.separator).concat(THUMB_PREFIX).concat(file.getName());
                    File thumbFile = new File(thumbUrl);
                    if (thumbFile.exists()) {
                        thumbFile.delete();
                    }

                    //获取大图路径
                    String bigUrl = file.getParent().concat(File.separator).concat(BIG_PREFIX).concat(file.getName());
                    File bigUrlFile = new File(bigUrl);
                    if (bigUrlFile.exists()) {
                        bigUrlFile.delete();
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            log.error("删除文件错误", e);
            throw LxtxException.newException(Constants.SYSERROR_FILE_DELETE_ERROR_CODE, Constants.SYSERROR_FILE_DELETE_ERROR_MSG);
        }
    }

    /**
     * <p>将文件转成base64 字符串</p>
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(File file) {
        FileInputStream inputStream = null;
        byte[] buffer = null;
        try {
            inputStream = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BASE64Encoder().encode(buffer);

    }

    /**
     * 根据byte数组，生成文件
     *
     * @param bfile
     * @param filePath
     * @param fileName
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            //判断文件目录是否存在
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * file转byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] fileToBytes(String filePath) {
        byte[] buffer = null;
        File file = new File(filePath);

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();

            byte[] b = new byte[1024];

            int n;

            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

            buffer = bos.toByteArray();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return buffer;
    }

    /**
     * 递归删除其子目录的所有文件夹及文件
     *
     * @param path
     */
    public static void delDir(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            File[] tmp = dir.listFiles();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].isDirectory()) {
                    delDir(path.concat(File.separator).concat(tmp[i].getName()));
                } else {
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }


    /**
     * byte数组转换成16进制字符串
     *
     * @param src add by sgh
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据文件流读取图片文件真实类型
     *
     * @param fileTypeByte
     * @return
     */
    public static String getTypeByStream(byte[] fileTypeByte) {

        String type = bytesToHexString(fileTypeByte).toUpperCase();
        if (type.contains("FFD8FF")) {
            return "jpg";
        } else if (type.contains("89504E47")) {
            return "png";
        } else if (type.contains("47494638")) {
            return "gif";
        } else if (type.contains("49492A00")) {
            return "tif";
        } else if (type.contains("424D")) {
            return "bmp";
        } else {
            return null;
        }
    }

    /**
     * 文件写入
     *
     * @param file
     * @param fileByte
     */
    public void writeFile(File file, byte[] fileByte) {
        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(fileByte);
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            log.error("文件上传失败", e);
            throw LxtxException.newException(Constants.SYSERROR_FILE_UPLOAD_ERROR_CODE, Constants.SYSERROR_FILE_UPLOAD_ERROR_MSG);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw LxtxException.newException(Constants.SYSERROR_FILE_UPLOAD_ERROR_CODE, Constants.SYSERROR_FILE_UPLOAD_ERROR_MSG);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                log.error("文件上传失败", e);
                throw LxtxException.newException(Constants.SYSERROR_FILE_UPLOAD_ERROR_CODE, Constants.SYSERROR_FILE_UPLOAD_ERROR_MSG);
            }
            log.info("保存文件：{}", file.getPath());
        }
    }

    public void compressImage(String filePath, String thumbPath, byte[] fileByte, float outputQuality) {
        try {
            log.info("原图路径：{}，缩略图路径：{}，压缩质量比例：{}", filePath, thumbPath, outputQuality);
            ByteArrayInputStream bInput = new ByteArrayInputStream(fileByte);
            Thumbnails.of(bInput).scale(1f).outputQuality(outputQuality).toFile(thumbPath);
            log.info("保存缩略图：{}", thumbPath);
        } catch (Exception e) {
            log.error("生成缩略图失败:{}", e.getMessage(), e);
            File thumbFile = new File(thumbPath);
            writeFile(thumbFile, fileByte);
        }

    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of("C:\\Users\\Administrator\\Desktop\\1.gif").scale(1f).outputQuality(0.5f).toFile("C:\\Users\\Administrator\\Desktop\\1.gif");
    }
}
