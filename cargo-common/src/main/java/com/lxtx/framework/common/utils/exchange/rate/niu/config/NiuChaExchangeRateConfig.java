package com.lxtx.framework.common.utils.exchange.rate.niu.config;

import com.lxtx.framework.common.utils.http.utils.HttpInvokerConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author CaiRH
 * @since 2018-08-22
 */
@Setter
@Getter
public class NiuChaExchangeRateConfig {

    private static final Logger logger = LoggerFactory.getLogger(NiuChaExchangeRateConfig.class);

    private static final String CONFIG = "/niu-cha-exchange-rate.properties";

    private static final String CONFIG_TEMPLATE = "/niu-cha-exchange-rate-template.properties";

    private volatile ConfigParams configParams;

    public NiuChaExchangeRateConfig() {
        init();
    }

    public void init() {
        InputStream in = HttpInvokerConfig.class.getResourceAsStream(CONFIG);
        if (in == null) {
            in = HttpInvokerConfig.class.getResourceAsStream(CONFIG_TEMPLATE);
            logger.info("Load niu-cha-exchange-rate config file is {}", CONFIG_TEMPLATE);
        }
        this.configParams = loadConfigParams(in);
    }


    private ConfigParams loadConfigParams(InputStream in) {
        Properties properties = new Properties();
        try {
            //加载配置文件
            properties.load(in);

            ConfigParams params = new ConfigParams();
            params.setBaseUrl(properties.getProperty("exchangeRate.niuCha.baseUrl"));
            params.setC2cPriceUrl(properties.getProperty("exchangeRate.niuCha.c2cPriceUrl"));
            params.setInvestDoubleUrl(properties.getProperty("exchangeRate.niuCha.investDoubleUrl"));
            return params;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("Error:close niu-cha-exchange-rate properties file", e);
                }
            }
        }
    }
}