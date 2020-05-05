package com.lxtx.framework.common.utils.qrcode;

import com.google.common.collect.Lists;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 二维码生成和读的工具类
 *
 * @author liyunhua
 * @date 2018-08-16 0016
 */
@Slf4j
public class QRCodeUtil {
    public static final Map<DecodeHintType, Object> HINTS = new EnumMap<>(DecodeHintType.class);
    public static final String WECHAT_PATTERN = "wxp://.*|weixin://wxpay.*|weixin：//wxpay";
    public static final String ALIPAY_PATTERN = "http.*alipay.com.*|.*alipay.com.*｜ALIPAY.com.*|ALIPAY.COM";

    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param outputStream 文件输出流路径
     * @param content      二维码携带信息
     * @param qrCodeSize   二维码图片大小
     * @param imageFormat  二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        //设置二维码纠错级别ＭＡＰ
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i - 100, j - 100, 1, 1);
                }
            }
        }
        return ImageIO.write(image, imageFormat, outputStream);
    }

    /**
     * 读二维码并输出携带的信息
     */
    public static void readQrCode(InputStream inputStream) throws IOException {
        //从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);
        //将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (ReaderException e) {
            e.printStackTrace();
        }
        System.out.println(result.getText());
    }

    private static final BASE64Encoder encoder = new BASE64Encoder();
    private static final BASE64Decoder decoder = new BASE64Decoder();

    private static final int BLACK = 0x000000;
    private static final int WHITE = 0xFFFFFF;

    /**
     * 1.创建最原始的二维码图片
     *
     * @param info
     * @return
     */
    private static BufferedImage createCodeImage(QrCodeModel info) {

        String contents = StringUtils.isEmpty(info.getContents()) ? "暂无内容" : info.getContents();// 获取正文
        int width = info.getWidth();// 宽度
        int height = info.getHeight();// 高度
        Map<EncodeHintType, Object> hint = new HashMap<>();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置二维码的纠错级别【级别分别为M L H Q
        // ，H纠错能力级别最高，约可纠错30%的数据码字】
        hint.put(EncodeHintType.CHARACTER_SET, info.getCharacter_set());// 设置二维码编码方式【UTF-8】
        hint.put(EncodeHintType.MARGIN, 0);

        MultiFormatWriter writer = new MultiFormatWriter();
        BufferedImage img = null;
        try {
            // 构建二维码图片
            // QR_CODE 一种矩阵二维码
            BitMatrix bm = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hint);
            int[] locationTopLeft = bm.getTopLeftOnBit();
            int[] locationBottomRight = bm.getBottomRightOnBit();
            info.setBottomStart(new int[] { locationTopLeft[0], locationBottomRight[1] });
            info.setBottomEnd(locationBottomRight);
            int w = bm.getWidth();
            int h = bm.getHeight();
            img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    img.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 1.创建最原始的二维码图片
     *
     * @param info
     * @return
     */
    public static String getCodeImageBinary(QrCodeModel info) {
        BufferedImage createCodeImage = createCodeImage(info);
        return getImageBinary(createCodeImage);
    }

    /**
     * 2.为二维码增加logo和二维码下文字 logo--可以为null 文字--可以为null或者空字符串""
     *
     * @param info
     * @param output
     */
    private static void dealLogoAndDesc(QrCodeModel info, OutputStream output) {
        // 获取原始二维码图片
        BufferedImage bm = createCodeImage(info);
        if(bm == null) {
            return;
        }
        // 获取Logo图片
        File logoFile = info.getLogoFile();
        int width = bm.getWidth();
        int height = bm.getHeight();
        Graphics g = bm.getGraphics();
        try {
            // 处理logo
            if (logoFile != null && logoFile.exists()) {
                BufferedImage logoImg = ImageIO.read(logoFile);
                int logoWidth = logoImg.getWidth();
                int logoHeight = logoImg.getHeight();
                float ratio = info.getLogoRatio();// 获取Logo所占二维码比例大小
                if (ratio > 0) {
                    logoWidth = logoWidth > width * ratio ? (int) (width * ratio) : logoWidth;
                    logoHeight = logoHeight > height * ratio ? (int) (height * ratio) : logoHeight;
                }
                int x = (width - logoWidth) / 2;
                int y = (height - logoHeight) / 2;
                // 根据logo 起始位置 和 宽高 在二维码图片上画出logo
                g.drawImage(logoImg, x, y, logoWidth, logoHeight, null);
            }

            // 处理二维码下文字
            String desc = info.getDesc();
            if (StringUtils.isNotBlank(desc)) {
                bm = setContent(info, bm, width, height, g, desc);
            }
            String format = info.getFormat();
            ImageIO.write(bm, format, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage setContent(QrCodeModel info, BufferedImage bm, int width, int height, Graphics g,
                                            String desc) {
        // 设置文字字体
        int whiteWidth = info.getHeight() - info.getBottomEnd()[1];
        Font font = new Font("黑体", Font.BOLD, info.getFontSize());
        int fontHeight = g.getFontMetrics(font).getHeight();
        // 计算需要多少行
        int lineNum = 1;
        int currentLineLen = 0;
        for (int i = 0; i < desc.length(); i++) {
            char c = desc.charAt(i);
            int charWidth = g.getFontMetrics(font).charWidth(c);
            if (currentLineLen + charWidth > width) {
                lineNum++;
                currentLineLen = 0;
                continue;
            }
            currentLineLen += charWidth;
        }
        int totalFontHeight = fontHeight * lineNum;
        int wordTopMargin = 4;
        BufferedImage bm1 = new BufferedImage(width, height + totalFontHeight + wordTopMargin - whiteWidth,
                BufferedImage.TYPE_INT_RGB);
        Graphics g1 = bm1.getGraphics();
        if (totalFontHeight + wordTopMargin - whiteWidth > 0) {
            g1.setColor(Color.WHITE);
            g1.fillRect(0, height, width, totalFontHeight + wordTopMargin - whiteWidth);
        }
        g1.setColor(new Color(BLACK));
        g1.setFont(font);
        g1.drawImage(bm, 0, 0, null);
        width = info.getBottomEnd()[0] - info.getBottomStart()[0];
        height = info.getBottomEnd()[1] + 1;
        currentLineLen = 0;
        int currentLineIndex = 0;
        int baseLo = g1.getFontMetrics().getAscent();
        for (int i = 0; i < desc.length(); i++) {
            String c = desc.substring(i, i + 1);
            int charWidth = g.getFontMetrics(font).stringWidth(c);
            if (currentLineLen + charWidth > width) {
                currentLineIndex++;
                currentLineLen = 0;
                g1.drawString(c, currentLineLen + whiteWidth,
                        height + baseLo + fontHeight * (currentLineIndex) + wordTopMargin);
                currentLineLen = charWidth;
                continue;
            }
            g1.drawString(c, currentLineLen + whiteWidth,
                    height + baseLo + fontHeight * (currentLineIndex) + wordTopMargin);
            currentLineLen += charWidth;
        }
        g1.dispose();
        bm = bm1;
        return bm;
    }

    /**
     * 3.创建 带logo和文字的二维码
     *
     * @param info
     * @param file
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createCodeImage(QrCodeModel info, File file) throws IOException {
        File parent = file.getParentFile();
        if (!parent.exists()){
            parent.mkdirs();
        }
        try(OutputStream output = new BufferedOutputStream(new FileOutputStream(file));) {
            dealLogoAndDesc(info, output);
            output.flush();
        }
    }

    /**
     * 3.创建 带logo和文字的二维码
     *
     * @param info
     * @param filePath
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createCodeImage(QrCodeModel info, String filePath) throws IOException {
        createCodeImage(info, new File(filePath));
    }

    /**
     * 4.创建 带logo和文字的二维码
     *
     * @param filePath
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void createCodeImage(String contents, String filePath) throws IOException {
        QrCodeModel codeModel = new QrCodeModel();
        codeModel.setContents(contents);
        createCodeImage(codeModel, new File(filePath));
    }

    /**
     * 5.读取 二维码 获取二维码中正文
     *
     * @param input
     * @return
     */
    public static String decode(InputStream input) {
        Map<DecodeHintType, Object> hint = new HashMap<>();
        hint.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);
        String result = "";
        try {
            BufferedImage img = ImageIO.read(input);
            int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
            LuminanceSource source = new RGBLuminanceSource(img.getWidth(), img.getHeight(), pixels);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            QRCodeReader reader = new QRCodeReader();
            Result r = reader.decode(bitmap, hint);
            result = r.getText();
        } catch (Exception e) {
            result = "读取错误";
        }
        return result;
    }

    public static String getImageBinary(BufferedImage bufferedImage) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage base64StringToImage(String base64String) throws IOException {
        BufferedImage bi = null;
        byte[] bytes = decoder.decodeBuffer(base64String);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);) {
            bi = ImageIO.read(bais);
        }
        return bi;
    }


    /**
     * 解析二维码链接
     * @param link
     * @return
     * @throws IOException
     */
    public static String parseLink(String link) {
        if(StringUtils.isBlank(link)){
            return null;
        }

        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new Request.Builder().url(link).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("下载图片失败={}", link);
            log.error(e.getMessage(),e);

        }

        if(null == response){
            return null;
        }
        if (!response.isSuccessful()) {
            log.error("下载图片失败,请稍后重试！:{} " , response);
            return null;
        }

        String realLink = null;
        try {
            byte[] bytes = response.body().bytes();
            realLink =  parseQRCode(bytes);
            if(null == realLink ){
                realLink =  parseQRCode1(bytes);
            }
            if(null == realLink ){
                realLink =  parseQRCode2(bytes);
            }
            if(null == realLink ){
                realLink =  parseQRCode3(bytes);
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return realLink;
    }


    public static  String  parseQRCode(byte[] bytes)  {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            //将图片转换成二进制图片
            BinaryBitmap binaryBitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            QRCodeReader reader = new QRCodeReader();//初始化解析对象
            try {
                Result result = reader.decode(binaryBitmap, HINTS);//开始解析
                return result.getText();
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }


    public static String parseQRCode1(byte[] bytes) {

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {

            BufferedImage image = ImageIO.read(inputStream);
            // QRCode解码器2
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            com.google.zxing.Result result = new MultiFormatReader().decode(binaryBitmap, hints);//  对图像进行解码
            return result.getText();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }


    public static  String  parseQRCode2(byte[] bytes)  {

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            //将图片转换成二进制图片
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            int width = binaryBitmap.getWidth();
            int height = binaryBitmap.getHeight();

            QRCodeReader reader = new QRCodeReader();//初始化解析对象
            try {
                Result result = reader.decode(binaryBitmap, HINTS);//开始解析
                return result.getText();
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }



    public static String  parseQRCode3(byte[] bytes) {

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            BufferedImage image = ImageIO.read(inputStream);
            // QRCode解码器1
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            Map<DecodeHintType, String> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            // 获取读取二维码结果
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            com.google.zxing.Result decode = multiFormatReader.decode(binaryBitmap, hints);
            return decode.getText();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 判断是否支付宝支付
     * e.g.:https://qr.alipay.com/mpx028683hprwy2xvyn6f2
     * @param payContent
     * @return
     */
    public static Boolean isAlipayQRCode(String payContent){
        if(StringUtils.isNotEmpty(payContent)){
            payContent = StringUtils.lowerCase(payContent);
        }
        boolean matches = Pattern.matches(QRCodeUtil.ALIPAY_PATTERN, payContent);
        return matches;
    }

    /**
     * 判断是否微信支付
     *
     * <blockquote>
     *  e.g:微信
     *        <p>wxp://f2f0KPndRAXPGhoQzi6RDcqNjaDe4q0FM</p>
     *        <p>weixin://wxpay/s/An4baqw</p>
     *       <p>weixin：//wxpay/s/An4baqw</p>
     *<blockquote>
     * @param payContent
     * @return
     */
    public static Boolean isWechatQRCode(String payContent){
        if(StringUtils.isNotEmpty(payContent)){
            payContent = StringUtils.lowerCase(payContent);
        }
        boolean matches = Pattern.matches(QRCodeUtil.WECHAT_PATTERN, payContent);
        return matches;
    }






    public static void main(String[] args) throws IOException, WriterException {
//        createQrCode(new FileOutputStream(new File("E:\\qrcode.jpg")), "ID13800138000", 900, "JPEG");
//        readQrCode(new FileInputStream(new File("E:\\qrcode.jpg")));
//        String link = "https://www.baidu.com/images/2013/weixin.png";
//        String link = "http://www.xitongcheng.cc/uploads/allimg/180627/19-1P62GJ344X7.jpg";
//        String link = "http://www.yundongfang.com/wp-content/uploads/2015/05/27.png";
//        String link = "http://web.qimengwifi.com/data/upload/201611/f_a82f37c36c225ce3941fa80ede984efb.jpg";
//        String link = "https://static.sucaihuo.com/uploads/course/2018/12/18/1545096971906.jpg";
//        String link = "http://static.lliao.net:8077/file/chat/20191213/414b6955c3d24117a01617afe7e98fe9.jpg";

        ArrayList<String> linkList = Lists.newArrayList(
                "http://148.66.11.232/new/secret/complete/userqrcode/20200213/9D3760897D9D4F9C9FCD7BDB25466DF8.jpeg",
                "https://www.baidu.com/images/2013/weixin.png"
                , "http://www.xitongcheng.cc/uploads/allimg/180627/19-1P62GJ344X7.jpg"
                , "http://www.yundongfang.com/wp-content/uploads/2015/05/27.png"
                , "http://web.qimengwifi.com/data/upload/201611/f_a82f37c36c225ce3941fa80ede984efb.jpg"
                , "https://static.sucaihuo.com/uploads/course/2018/12/18/1545096971906.jpg"
                , "http://static.lliao.net:8077/file/chat/20191213/414b6955c3d24117a01617afe7e98fe9.jpg"
                , "http://static.lliao.net:8077/file/chat/20191213/7f679d65e7eb4fb896339234054f3540.jpg"
                , "https://pay.weixin.qq.com/wiki/doc/api/img/chapter6_5_2.png"
                , "https://gss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=a90768bfc695d143da23ec2543c0ae3a/9d82d158ccbf6c816baaa854b23eb13532fa40e9.jpg"
        );

//        String link = "http://static.lliao.net:8077/file/chat/20191213/7f679d65e7eb4fb896339234054f3540.jpg";
        linkList.forEach(l->{
            String s = parseLink(l);
            log.info("图片链接={} ,解析后={}" , l, s);
        });
//        String alipayLink = "https://qr.alipay.com/mpx028683hprwy2xvyn6f2";
//        String alipayLink = "HTTPS://QR.ALIPAY.COM/FKX00228HHHBJW0XBHCX0C";
        String alipayLink = "HTTPS://QR.ALIPAY.COM/FKX02583PWZZMQSBHLPV54?t=1581596548174";
        Boolean alipayQRCode = isAlipayQRCode(alipayLink);
        log.info("alipayQRCode={}",alipayQRCode);


        String wechatLink = "wxp://f2f0KPndRAXPGhoQzi6RDcqNjgdFaDe4q0FM";
        Boolean wechat = isWechatQRCode(wechatLink);
        log.info("wechat={}",wechat);

    }


}
