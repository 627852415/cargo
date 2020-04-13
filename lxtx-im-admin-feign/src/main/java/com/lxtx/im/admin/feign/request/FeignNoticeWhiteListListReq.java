package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeignNoticeWhiteListListReq extends BasePageReq {


   /**
    * 钱包用户ID
    */
   private String userId;

   /**
    *
    */
   private Integer type;


}
