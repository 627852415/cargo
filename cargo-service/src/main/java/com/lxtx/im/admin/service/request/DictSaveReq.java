package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
* @description:  字典保存入参
* @author:   CXM
* @create:   2018-10-12 15:26
*/
@Data
public class DictSaveReq {
    /**
     * 主键
     */
    private String gid;
    /**
     * 参数说明
     */
    @NotBlank(message = "描述不能为空")
    private String description;
    /**
     * 参数所属的域
     */
    @NotBlank(message = "模块不能为空")
    private String domain;
    /**
     * 配置参数key（mysql里面key是关键字不能作为列名，所以前面加个i）
     */
    @NotBlank(message = "key不能为空")
    private String ikey;
    /**
     * 参数值
     */
    @NotBlank(message = "value不能为空")
    private String value;
}
