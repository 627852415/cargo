package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:  广告位详情
 * @author:   xufeifei
 * @create:   2020-2-24
 */
@Getter
@Setter
public class AdPositionDetailByIdReq {

    /**
     * 广告位id
     */
    @NotBlank(message = "广告位id不能为空")
    private String id;
}
