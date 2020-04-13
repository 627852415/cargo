package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 用户身份认证详情请求类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class UserAuthenticationDetailReq {

    /**
     * Id
     */
    @NotBlank(message = "用户身份认证数据Id不能为空")
    private String id;

    /**
     * 判断是否为修改
     */
    private Boolean edit = false;

}
