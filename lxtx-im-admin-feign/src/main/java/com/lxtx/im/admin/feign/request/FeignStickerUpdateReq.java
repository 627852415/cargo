package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
* @description:  更新表情包
* @author:   CXM
* @create:   2018-12-21 14:24
*/
@Setter
@Getter
public class FeignStickerUpdateReq {
    /**
     * 表情包名称
     */
    private String id;

    /**
     * 表情包状态，0正常，1下架
     */
    private Integer status;
    /**
     * 表情包名称
     */
    private String name;
    /**
     * 封面
     */
    private String cover;
    /**
     * 表情包价格
     */
    private BigDecimal price;
    /**
     * 表情包宽度
     */
    private Integer width;

    /**
     * 表情包高度
     */
    private Integer height;
}
