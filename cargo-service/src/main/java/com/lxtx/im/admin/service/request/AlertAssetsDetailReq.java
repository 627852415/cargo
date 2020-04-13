package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/20 13:37
 */
@Getter
@Setter
public class AlertAssetsDetailReq {
    @NotBlank(message = "报警id不能为空")
    private String id;
}
