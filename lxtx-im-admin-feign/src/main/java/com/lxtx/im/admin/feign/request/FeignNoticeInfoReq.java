package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
* @description:  币种交易监控通知信息
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class FeignNoticeInfoReq {
    /**
     * 通知类型
     */
    private String domain;

}
