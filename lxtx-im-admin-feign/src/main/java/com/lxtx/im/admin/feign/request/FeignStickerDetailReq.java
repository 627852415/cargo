package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  表情包详情
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class FeignStickerDetailReq {
    /**
     * 表情包id
     */
    private String id;
}
