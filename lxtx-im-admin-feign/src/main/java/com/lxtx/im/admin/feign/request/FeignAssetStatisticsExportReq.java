package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 *    资产统计请求参数
  * @author Lin hj
  * @date 2019/6/14 11:11
*/
@Setter
@Getter
public class FeignAssetStatisticsExportReq extends BasePageReq {
   private Long recordsDate;

   /**
    * 币种ID
    */
   private String coinId;
}
