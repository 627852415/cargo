package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
* @description:  请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class FeignStickerSaveReq {
    /**
     * 表情包名称
     */
    private String name;

    /**
     * 表情包状态，0正常，1下架
     */
    private Integer status;

    /**
     * 封面图字符串
     */
    private String coverFileUrl;

    /**
     * 表情包压缩字符串
     */
    private String detailFileUrl;
    /**
     * 表情包宽度
     */
    private Integer width;

    /**
     * 表情包高度
     */
    private Integer height;

}
