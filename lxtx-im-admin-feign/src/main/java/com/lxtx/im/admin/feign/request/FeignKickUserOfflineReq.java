package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @description: 用户个人信息
 * @author: CXM
 * @create: 2018-08-15 16:52
 **/
@Setter
@Getter
public class FeignKickUserOfflineReq {
    @NotBlank(message = "errors.user.account.isNull")
    private String account;
}
