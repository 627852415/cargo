package com.lxtx.im.admin.service.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class OffsiteExchangeUtil {

	public static String formatExchangeRateToStr(BigDecimal exchangeRate) {
		String bigDecimalStr = "";
        DecimalFormat df = new DecimalFormat();
        if(exchangeRate.compareTo(new BigDecimal("0.01"))==-1) {
        	df.applyPattern("0.00######");
            bigDecimalStr = df.format(exchangeRate.setScale(8, BigDecimal.ROUND_DOWN));
		}else {
			df.applyPattern("0.00");
            bigDecimalStr = df.format(exchangeRate.setScale(2, BigDecimal.ROUND_DOWN));
		}
        return bigDecimalStr;
	}
	
}
