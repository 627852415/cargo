package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  表情包内表情列表请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class FeignStickerDetailListReq extends BasePageReq {
    /**
     * 表情包id
     */
    private String id;

}
