package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
* @description:  字典保存入参
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class FeignDictSaveReq {
    /**
     * 主键
     */
    private String gid;
    /**
     * 参数说明
     */
    private String description;
    /**
     * 参数所属的域
     */
    private String domain;
    /**
     * 配置参数key（mysql里面key是关键字不能作为列名，所以前面加个i）
     */
    private String ikey;
    /**
     * 参数值
     */
    private String value;
}
