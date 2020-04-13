package com.lxtx.im.admin.service.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 对账详情分页
 *
 * @author Ppai
 * @since 2019-03-12
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayCheckDetailPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<PayCheckDetailResp> records;
}
