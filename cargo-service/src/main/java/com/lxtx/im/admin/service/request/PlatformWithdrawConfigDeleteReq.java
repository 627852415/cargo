package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 平台提款配置主键
 *
 * @author CaiRH
 */
@Setter
@Getter
public class PlatformWithdrawConfigDeleteReq {

    /**
     * 主键
     */
    @NotBlank(message = "提款配置主键ID不能为空")
    private String id;

}
