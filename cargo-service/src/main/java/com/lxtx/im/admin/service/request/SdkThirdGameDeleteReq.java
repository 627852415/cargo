package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  删除第三方游戏参数类
* @author:   CXM
* @create:   2018-12-01 9:58
*/
@Getter
@Setter
public class SdkThirdGameDeleteReq {
    /**
     * 游戏id
     */
    @NotBlank(message = "游戏id不能为空")
    private String id;
}
