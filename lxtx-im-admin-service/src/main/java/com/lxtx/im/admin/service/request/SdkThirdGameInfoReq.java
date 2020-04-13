package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  根据id获取第三方游戏信息
* @author:   CXM
* @create:   2018-11-30 13:59
*/
@Setter
@Getter
public class SdkThirdGameInfoReq {
    /**
     * 游戏id
     */
    @NotBlank(message = "游戏id不能为空")
    private String id;
}
