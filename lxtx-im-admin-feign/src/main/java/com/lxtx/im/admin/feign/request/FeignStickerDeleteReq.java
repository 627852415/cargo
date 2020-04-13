package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  删除表情包
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class FeignStickerDeleteReq {
    /**
     * 表情包id
     */
    private String id;
}
