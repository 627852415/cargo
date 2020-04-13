package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
  *    USB TOKEN 请求参数
 　　* @author Lin hj
 　　* @date 2019/5/16 11:05
 */
@Setter
@Getter
public class UsbTokenReq implements Serializable {
    private String time;
    private String sign;
    private String cert;
    private String ca;
    private String token;
    private String userName;
    private String ip;
}
