package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  获取表情包详情
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class StickerDetailReq {
    /**
     * 表情包id
     */
    @NotBlank(message = "表情包id不能为空")
    private String id;
}
