package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  根据domain和key修改对应的值
* @create:   2018-04-09
*/
@Setter
@Getter
public class DictModifyValueReq {
    /**
     * 域
     */
    @NotBlank(message = "domain不能为空")
    private String domain;
    /**
     * key值
     */
    @NotBlank(message = "ikey不能为空")
    private String ikey;
    /**
     * value值
     */
    @NotBlank(message = "value不能为空")
    private String value;

}
