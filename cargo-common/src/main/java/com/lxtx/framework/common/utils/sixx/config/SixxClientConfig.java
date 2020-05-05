package com.lxtx.framework.common.utils.sixx.config;

import com.lxtx.framework.common.utils.http.utils.HttpInvokerConfig;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: Czh
 * Date: 2018/8/16 下午2:30
 */
@Data
public class SixxClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(SixxClientConfig.class);

    private static final String CONFIG = "/6x-client.properties";

    private static final String CONFIG_TEMPLATE = "/6x-client-template.properties";

    private volatile ConfigParams configParams;

    public SixxClientConfig() {
        init();
    }

    public void init() {
        InputStream in = HttpInvokerConfig.class.getResourceAsStream(CONFIG);
        if (in == null) {
            in = HttpInvokerConfig.class.getResourceAsStream(CONFIG_TEMPLATE);
            logger.info("Load 6x-client config file is {}", CONFIG_TEMPLATE);
        }
        this.configParams = loadConfigParams(in);
    }


    private ConfigParams loadConfigParams(InputStream in) {
        Properties properties = new Properties();
        try {
            //加载配置文件
            properties.load(in);

            ConfigParams params = new ConfigParams();
            params.setHttp(properties.getProperty("6x.client.http"));
            params.setHost(properties.getProperty("6x.client.host"));
            params.setApiSecretKey(properties.getProperty("6x.client.apiSecretKey"));
            params.setAccessKeyId(Integer.valueOf(properties.getProperty("6x.client.accesskeyId")));
            return params;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("Error:close 6x-client properties file", e);
                }
            }
        }
    }
}