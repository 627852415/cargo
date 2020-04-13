package com.lxtx.im.admin.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * 对账列表分页
 *
 * @author Ppai
 * @since 2019-03-12
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayBillCheckRecordPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<PayBillCheckRecordResp> records;
}
