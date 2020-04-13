package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:  广告详情
 * @author:   xufeifei
 * @create:   2020-2-24
 */
@Getter
@Setter
public class FeignAdDetailByIdReq {

    /**
     * 广告id
     */
    @NotBlank(message = "广告id不能为空")
    private String id;
}
