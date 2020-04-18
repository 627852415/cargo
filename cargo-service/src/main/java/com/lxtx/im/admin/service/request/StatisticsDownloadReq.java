package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 文件导出入参
 * @author: CXM
 * @create: 2018-09-11 15:20
 */
@Getter
@Setter
public class StatisticsDownloadReq extends BasePageReq {

    /**
     * 平台类型
     */
    private Integer platformType;

}
