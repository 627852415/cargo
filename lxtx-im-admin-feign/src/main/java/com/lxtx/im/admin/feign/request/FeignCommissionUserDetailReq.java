package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: 返佣伙伴详情
 * @author: xufeifei
 * @date: 2020-03-07
 */
@Setter
@Getter
public class FeignCommissionUserDetailReq {

    @NotBlank(message = "id不能为空")
    private String id;
}
