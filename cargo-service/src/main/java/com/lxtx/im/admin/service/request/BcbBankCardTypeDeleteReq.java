package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/3 11:41
 */
@Setter
@Getter
public class BcbBankCardTypeDeleteReq {
    /**
     * 主键
     */
    @NotBlank(message = "ID不能为空")
    private String id;
}
