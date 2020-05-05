package com.lxtx.framework.common.utils.sixx.config;

import lombok.Data;

/**
 * @author: Czh
 * Date: 2018/8/16 下午2:29
 */
@Data
public class ConfigParams {

    private String host;

    private String http;

    private String apiSecretKey;

    private Integer accessKeyId;
}