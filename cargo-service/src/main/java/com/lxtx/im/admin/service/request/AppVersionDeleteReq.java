package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author tangdy
 */
@Setter
@Getter
public class AppVersionDeleteReq {
    /**
     * 主键
     */
    @NotBlank(message = "ID不能为空")
    private String id;
}
