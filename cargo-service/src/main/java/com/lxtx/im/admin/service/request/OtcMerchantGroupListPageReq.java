package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liyunhua
 * @Date: 2019/4/2
 */
@Setter
@Getter
public class OtcMerchantGroupListPageReq extends BasePageReq {

    /**
     * 描述
     */
    private String description;

    /**
     * 域，当该字段不为空的时候，过滤数据
     */
    private String domainName;
}
