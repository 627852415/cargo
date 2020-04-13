package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 平台提款配置主键
 *
 * @author CaiRH
 */
@Setter
@Getter
public class FeignPlatformWithdrawConfigDeleteReq {

    /**
     * 主键
     */
    @NotBlank(message = "提款配置主键ID不能为空")
    private String id;

}
