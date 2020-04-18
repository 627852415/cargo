package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description: 空投详情
 * @author: CXM
 * @create: 2018-09-01 13:50
 **/
@Setter
@Getter
public class AirdropDetailReq extends BasePageReq {
    @NotBlank(message = "空投id不能为空")
    private String id;
}
