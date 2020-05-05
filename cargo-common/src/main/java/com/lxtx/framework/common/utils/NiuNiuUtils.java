package com.lxtx.framework.common.utils;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Random;

import static java.math.BigDecimal.ROUND_FLOOR;

@Getter
@Setter
@Slf4j
/**
 *  牛牛工具类
 * @author LiuLP
 * @date 2018/11/11
 */
public class NiuNiuUtils {


    public static int getNiuNiu(int[] card){
        int cardsTotal = 0;
        int cow = -1;
        for(int i=0;i<card.length;i++){
            cardsTotal +=card[i];
        }
        for(int i=0;i<4;i++ ){
            for(int j=i+1;j<5;j++ ){
                if(i != j){
                    if((cardsTotal-card[i]-card[j])%10==0){
                        cow = (card[i]+card[j])%10;
                        return cow;
                    }
                }

            }
        }
        return cow;
    }

    /**
     * 获取小数点后五位数的牛牛
     * @param amount
     * @return
     */
    public static int getNiuNiu(BigDecimal amount){
        String formatAmount = amount.setScale(5, ROUND_FLOOR).toPlainString();
        // 后5位数字
        String fiveNum = formatAmount.substring(formatAmount.length() - 5);
        char[] chars = fiveNum.toCharArray();
        int[] fiveNumArray = new int[chars.length];
        for(int i = 0;i < chars.length; i++){
            fiveNumArray[i] = Integer.parseInt(chars[i] + "");
        }
       return getNiuNiu(fiveNumArray);
    }

    public static Integer getTotal(BigDecimal amount) {
        BigDecimal divide = amount.divide(BigDecimal.valueOf(1), 5, ROUND_FLOOR);
        int i = (divide + "").indexOf(".");
        String substring = (divide + "").substring(i + 1, i + 6);
        char[] ch = substring.toCharArray();
        int sum = 0;
        for (char s : ch) {
            sum = sum + (s - '0');
        }

        return sum;
    }

    /**
     * niuniu游戏获取5位随机数
     * @param num
     * @param amount
     * @return
     */
    public static BigDecimal randomFiveNumber(BigDecimal num , BigDecimal amount){
        Random ne=new Random();
        int x=ne.nextInt(9999-1000+1)+1000;
        BigDecimal bigDecimal = new BigDecimal(x);
        BigDecimal decimal = bigDecimal.divide(BigDecimal.valueOf(100000), 5, ROUND_FLOOR);
        BigDecimal divide = amount.divide(num,1,ROUND_FLOOR);
        BigDecimal result = divide.add(decimal);
        return result;

    }

    public static void main(String[] args){


    }
}