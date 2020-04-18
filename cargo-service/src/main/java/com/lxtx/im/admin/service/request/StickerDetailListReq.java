package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  表情包内表情列表请求参数
* @author:   CXM
* @create:   2018-12-19 14:05
*/
@Setter
@Getter
public class StickerDetailListReq extends BasePageReq {
    /**
     * 表情包id
     */
    @NotBlank(message = "表情包id不能为空")
    private String id;

}
