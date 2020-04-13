package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取字典列表入参
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class DictListPageReq extends BasePageReq {
    /**
     * 描述
     */
    private String description;
    /**
     * 域
     */
    private String domain;
    /**
     * key值
     */
    private String ikey;
    /**
     * 域，用来过滤模块数据
     *
     */
    private String domainName;
    private String value;
}
