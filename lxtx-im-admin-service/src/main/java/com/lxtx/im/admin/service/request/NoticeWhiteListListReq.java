package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeWhiteListListReq extends BasePageReq {


   /**
    * 钱包用户ID
    */
   private String userId;

   /**
    *
    */
   private Integer type;


}
