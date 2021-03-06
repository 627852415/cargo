package com.lxtx.framework.common.utils.exchange.rate.config;

import com.lxtx.framework.common.utils.http.utils.HttpInvokerConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Czh
 * Date: 2018/8/22 上午11:59
 */
@Setter
@Getter
public class ExchangeRateConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateConfig.class);

    private static final String CONFIG = "/exchange-rate.properties";

    private static final String CONFIG_TEMPLATE = "/exchange-rate-template.properties";

    private volatile ConfigParams configParams;

    public ExchangeRateConfig() {
        init();
    }

    public void init() {
        InputStream in = HttpInvokerConfig.class.getResourceAsStream(CONFIG);
        if (in == null) {
            in = HttpInvokerConfig.class.getResourceAsStream(CONFIG_TEMPLATE);
            logger.info("Load exchange-rate config file is {}", CONFIG_TEMPLATE);
        }
        this.configParams = loadConfigParams(in);
    }


    private ConfigParams loadConfigParams(InputStream in) {
        Properties properties = new Properties();
        try {
            //加载配置文件
            properties.load(in);

            ConfigParams params = new ConfigParams();
            params.setAppId(properties.getProperty("exchangeRate.apiId"));
            params.setSymbols(properties.getProperty("exchangeRate.symbols"));
            params.setBaseUrl(properties.getProperty("exchangeRate.baseUrl"));
            return params;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("Error:close exchange-rate properties file", e);
                }
            }
        }
    }
}