package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @description: 查询对账详情列表
 * @author: Ppai
 * @create: 2019-03-12 13:50
 **/
@Getter
@Setter
public class FeignPayCheckDetailReq extends BasePageReq {

    @NotBlank(message = "账单ID不能为空")
    private String userId;

    private String billId;

    private Date start;

    private Date end;

    private boolean searchAll;
}
