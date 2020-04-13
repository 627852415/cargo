package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* @description:  请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class StickerSaveReq {
    /**
     * 表情包名称
     */
    @NotBlank(message = "表情包名称不能为空")
    private String name;

    /**
     * 表情包状态，0正常，1下架
     */
    @NotNull(message = "表情包状态不能为空")
    private Integer status;

    /**
     * 表情包宽度
     */
    @NotNull(message = "表情包width不能为空")
    private Integer width;

    /**
     * 表情包高度
     */
    @NotNull(message = "表情包height不能为空")
    private Integer height;

}
