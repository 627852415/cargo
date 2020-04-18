package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: otc每日限额
 * @author: CXM
 * @create: 2018-08-31 09:56
 **/
@Setter
@Getter
public class OtcDailyLimitListPageReq extends BasePageReq {
    /**
     * 描述
     */
    private String description;
    /**
     * 域，当该字段不为空的时候，过滤数据
     */
    private String domainName;
}
