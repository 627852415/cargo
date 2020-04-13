package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:  更新用户返佣权限
 * @author:   xufeifei
 * @create:   2020-03-09
 */
@Setter
@Getter
public class FeignCommissionUserUpdateReq {

    private String id;

    /**
     * 状态【1：启用，2：禁用】
     */
    private Integer status;
}
