package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/2 18:07
 */
@Getter
@Setter
public class BcbBankCardTypeDetailReq {
    @NotBlank(message = "id不能为空")
    private String id;
}
