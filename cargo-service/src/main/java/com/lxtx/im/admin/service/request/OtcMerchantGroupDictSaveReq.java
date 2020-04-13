package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * OTC商家分组配置参数
 *
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
@Data
public class OtcMerchantGroupDictSaveReq {

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
    private String value;
}
