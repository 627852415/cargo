package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 交易记录详情请求参数
 *
 * @author CaiRH
 * @since 2019-11-22
 */
@Setter
@Getter
public class TransactionDetailReq {

    @NotBlank(message = "id不能为空")
    private String id;
}
