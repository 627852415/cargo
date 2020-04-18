package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
* @description:  修改第三方游戏状态
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class SdkUpdateThirdGameStatusReq {
    /**
     * 游戏id
     */
    @NotBlank(message = "游戏id不能为空")
    private String id;
    /**
     * 游戏状态
     */
    @NotNull(message = "游戏status不能为空")
    private Boolean status;
}
