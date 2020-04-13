package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/27 15:08
 */
@Setter
@Getter
public class CapitalDetailReq {
    @NotBlank(message = "id不能为空")
    private String id;
}
