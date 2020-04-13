package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.math.BigDecimal;

/**
* @description:  根据表情包id详情返回
* @author:   CXM
* @create:   2018-12-21 18:18
*/
@Data
public class StickerDetailByIdResp {
    /**
     * 主键
     */
    private String id;
    /**
     *
     *
     * 表情包名
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
