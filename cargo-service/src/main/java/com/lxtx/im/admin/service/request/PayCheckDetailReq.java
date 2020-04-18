package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description: 查询对账详情列表
 * @author: Ppai
 * @create: 2019-03-12 13:50
 **/
@Getter
@Setter
public class PayCheckDetailReq extends BasePageReq {

    @NotBlank(message = "账单ID不能为空")
    private String userId;

    private String billId;

    private String start;

    private String end;

    private boolean searchAll;
}
