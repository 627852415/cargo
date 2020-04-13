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
public class DictCoinMaxListPageReq extends BasePageReq {
    /**
     * 描述
     */
    private String description;
    /**
     * key值
     */
    private String ikey;
    /**
     * 域，当该字段不为空的时候，过滤数据
     */
    private String domainName;
}
