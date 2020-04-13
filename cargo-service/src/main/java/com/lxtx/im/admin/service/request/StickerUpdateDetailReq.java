package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  表情内容更新
* @author:   CXM
* @create:   2018-12-21 14:24
*/
@Setter
@Getter
public class StickerUpdateDetailReq {
    /**
     * 表情包名称
     */
    @NotBlank(message = "表情id不能为空")
    private String id;
    /**
     * 字段名称
     */
    @NotBlank(message = "name不能为空")
    private String name;
    /**
     * 值
     */
    @NotBlank(message = "value不能为空")
    private String value;

}
