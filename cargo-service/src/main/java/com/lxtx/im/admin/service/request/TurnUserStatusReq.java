package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 更改用户状态请求参数
 *
 * @author CaiRH
 * @since 2018-09-11
 */
@Setter
@Getter
public class TurnUserStatusReq {

    /**
     * 用户id
     */
    @NotBlank(message = "userId不能为空")
    private String userId;

    /**
     * 是否禁用
     */
    @NotNull(message = "forbiddenFlag不能为空")
    private Boolean forbiddenFlag;

}