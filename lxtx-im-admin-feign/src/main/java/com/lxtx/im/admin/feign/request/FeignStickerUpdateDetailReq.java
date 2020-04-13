package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  表情内容更新
* @author:   CXM
* @create:   2018-12-21 14:24
*/
@Setter
@Getter
public class FeignStickerUpdateDetailReq {
    /**
     * 表情包名称
     */
    private String id;
    /**
     * 字段名称
     */
    private String name;
    /**
     * 值
     */
    private String value;

}
