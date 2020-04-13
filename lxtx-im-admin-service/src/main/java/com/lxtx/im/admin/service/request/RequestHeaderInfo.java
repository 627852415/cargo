package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 请求头参数
 *
 * @Author: liyunhua
 * @Date: 2019/3/14
 */
@Getter
@Setter
public class RequestHeaderInfo {

    /**
     * 渠道号
     */
    private String channel;

    /**
     * 要控制的版本号
     */
    private String version;

}