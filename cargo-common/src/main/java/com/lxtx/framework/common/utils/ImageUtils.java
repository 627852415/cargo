package com.lxtx.framework.common.utils;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
@Slf4j
/**
 *  图片处理工具类
 * @author LiuLP
 * @date 2018/11/11
 */
public class ImageUtils {

    /**
     * 缩略图前缀
     */
    private static final String THUMB_SUFFIX = "thumb_";

    /**
     * 默认输出图片宽
     */
    private static int outputWidth;
    /**
     * 默认输出图片高
     */
    private static int outputHeight ;


    /**
     *  生成缩略图
     * @param inputDir
     * @param inputFileName
     * @param proportion  是否等比缩小或放大图片
     * @return
     */
    public static Boolean compressPic(String inputDir,String inputFileName,boolean proportion) {
        try {
            //获得源文件
            File file = new File(inputDir + inputFileName);
            if (!file.exists()) {
                throw LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE,"文件不存在");
            }
            Image img = ImageIO.read(file);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                throw LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE,"图片格式不正确");
            } else {
                int newWidth;
                int newHeight;
                // 判断是否是等比缩放
                if (proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (((double) img.getWidth(null)) / rate);
                    newHeight = (int) (((double) img.getHeight(null)) / rate);
                } else {
                    // 输出的图片宽度
                    newWidth = img.getWidth(null);
                    // 输出的图片高度
                    newHeight = img.getHeight(null);
                }
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
                 /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
                 * 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                // 输出图路径
                String outputDir = inputDir;

                // 输出图文件名
                String outputFileName = THUMB_SUFFIX + inputFileName;
                OutputStream out = new FileOutputStream(outputDir + outputFileName);
                ImageIO.write((RenderedImage) img, "jpg", out);
                out.close();
                return true;
            }
        } catch (IOException ex) {
            log.error("缩略图生成失败",ex);
            throw LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE,"缩略图生成失败");
        }
    }


    /**
     * 生成等比缩略图
     * @param inputDir
     * @param inputFileName
     * @param width
     * @param height
     * @return
     */
    public static Boolean compressPic(String inputDir, String inputFileName, int width, int height) {

        // 设置图片长宽
        outputWidth = width;
        outputHeight = height;
        return compressPic(inputDir,inputFileName,true);
    }


    /**
     * 生成原始尺寸缩略图
     * @param inputDir
     * @param inputFileName
     * @return
     */
    public static Boolean compressPic(String inputDir, String inputFileName) {

        return compressPic(inputDir,inputFileName,false);
    }

    /**
     * 根据URL获取图片base编码格式（图片会压缩）
     * @param url
     * @return
     */
    public static String getBase64ByImgUrl(String url){
        String suffix = url.substring(url.lastIndexOf(".") + 1);
        try {
            URL urls = new URL(url);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Image image = Toolkit.getDefaultToolkit().getImage(urls);
            BufferedImage  biOut = toBufferedImage(image);
            ImageIO.write(biOut, suffix, baos);
            String base64Str = Base64Util.encode(baos.toByteArray());
            return base64Str;
        } catch (Exception e) {
            return "";
        }

    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    /**
     * 通过图片的url获取图片的base64字符串
     * @param imgUrl    图片url
     * @return    返回图片base64的字符串
     */
    public static String image2Base64(String imgUrl) {

        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;

        try{
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();

            outStream = new ByteArrayOutputStream();

            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];

            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;

            //使用一个输入流从buffer里把数据读取出来
            while( (len=is.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }

            // 对字节数组Base64编码
            return Base64Util.encode(outStream.toByteArray());

        }catch (Exception e) {
            e.printStackTrace();
        }

        finally{
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outStream != null)
            {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpUrl != null)
            {
                httpUrl.disconnect();

            }

        }

        return null;

    }

    public static void main(String[] args) throws IOException {
//        compressPic("d:\\",  "123.png");
        //scale 压缩后的图片大小，outputQuality 压缩后的图片质量
        //Thumbnails.of("d:\\12.png").scale(0.5f).outputQuality(0.5f).toFile("d:\\thumb_111.jpg");
        String ss = image2Base64("http://pic1.win4000.com/wallpaper/9/5450ae2fdef8a.jpg");
        GenerateImage(ss,"E:\\wangyc2.jpg");
        System.out.println(ss);
    }

    public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}