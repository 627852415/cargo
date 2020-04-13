package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Czh
 * Date: 2019-06-25 15:51
 */
@Setter
@Getter
public class WithdrawConfigDeleteReq {

    @NotBlank(message = "id不能为空")
    private String id;
}