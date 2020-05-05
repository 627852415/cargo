package com.lxtx.framework.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Enumeration;

/**
 * 解压
 * @author 唐大用
 * @version 1.0
 * @date 2018年13月22日下午3:25:19
 */
public class DeCompressUtils {

    private static int BUF_SIZE = 1024;

    /**
     * 压缩格式
     */
    public static final String COMPRESS_ZIP = "ZIP";
    public static final String COMPRESS_RAR = "RAR";
    public static final String COMPRESS_7Z = "7Z";


    /**
     * 解压缩文件
     * @param multipartFile
     * @param targetPath 目标文件夹
     * ZIP
     * @param request
     */
    public static void deCompress(MultipartFile multipartFile, String targetPath, HttpServletRequest request){
        File file = null;
        try {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            //获取文件名
            String sourceFile = multipartFile.getOriginalFilename();

            if(StringUtils.isBlank(sourceFile)){
                return;
            }

            String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
            File dir = new File(filePath);
            if(! dir.exists()) {
                dir.mkdir();
            }

            String path = filePath + sourceFile;
            file =  new File(path);
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, file);

            String fileType = sourceFile.substring(sourceFile.lastIndexOf(".") + 1).toUpperCase();
            String fileName = sourceFile.substring(0,sourceFile.lastIndexOf("."));
            switch (fileType) {
                case COMPRESS_ZIP:{
                    unZip(file, targetPath + File.separator + fileName);
                    break;
                }
                default:
                    throw new Exception("解压文件格式错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(file != null && file.exists()){
                file.delete();
            }
        }
    }

    /**
     * 解压缩文件
     * @param filePath 文件路径
     * @param targetPath 目标文件夹
     */
    public static void deCompress(String filePath, String targetPath){
        File file = null;
        try {
            file = new File(filePath);
            unZip(file, targetPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(file != null && file.exists()){
                file.delete();
            }
        }
    }


    private static boolean isMessyCode( String str )
    {
        for( int i = 0; i < str.length(); i++ )
        {
            char c = str.charAt( i ) ;
            // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
            // 从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
            if( (int)c == 0xfffd )
            {
                // 存在乱码
                return true ;
            }
        }
        return false ;
    }

    public static void unZip(File unZipFile, String outputDirectory)
            throws Exception {
        // 创建输出文件夹对象
        File outDirFile = new File(outputDirectory);
        outDirFile.mkdirs();
        // 打开压缩文件文件夹
        ZipFile zipFile = getZipFile(unZipFile);
        for (Enumeration entries = zipFile.getEntries(); entries.hasMoreElements();) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            File file = new File(outDirFile, ze.getName());
            //创建目录
            if (ze.isDirectory()) {
                file.mkdirs();
            } else {
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = zipFile.getInputStream(ze);
                inStream2outStream(is, fos);
                fos.close();
                is.close();
            }
        }
        zipFile.close();
    }

    private static ZipFile getZipFile(File unZipFile) throws IOException {
        ZipFile zipFile = new ZipFile(unZipFile, "GBK");
        boolean flag = false;
        for (Enumeration entries = zipFile.getEntries(); entries.hasMoreElements();) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            String fileName = ze.getName();
            if(isMessyCode(fileName)){
                flag = true;
                break;
            }
        }
        if(flag){
            zipFile = new ZipFile(unZipFile, "UTF-8");
        }
        return zipFile;
    }

    private static void inStream2outStream(InputStream is, OutputStream os)
            throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        int bytesRead = 0;
        for (byte[] buffer = new byte[BUF_SIZE]; ((bytesRead = bis.read(buffer,
                0, BUF_SIZE)) != -1);) {
            // 将流写入
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
    }
}

