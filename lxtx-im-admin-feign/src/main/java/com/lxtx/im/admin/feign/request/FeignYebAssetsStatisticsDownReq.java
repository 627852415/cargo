package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 下载余额宝资产统计表 feign入参封装
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@Getter
@Setter
public class FeignYebAssetsStatisticsDownReq {

    /**
     * ID
     */
    private String id;

}
