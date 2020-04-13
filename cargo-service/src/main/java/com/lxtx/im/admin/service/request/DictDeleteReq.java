package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
* @description:  删除字典参数类
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class DictDeleteReq {
    /**
     * 字典ID
     */
    @NotBlank(message = "字典ID不能为空")
    private String gid;
}
