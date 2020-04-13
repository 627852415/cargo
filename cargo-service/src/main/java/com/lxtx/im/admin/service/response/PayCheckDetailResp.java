package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ppai
 * Date: 2019/3/12 上午11:53
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayCheckDetailResp {

    private String userId;

    @ExcelField(name = "账单ID", orderBy = "1")
    private String billId;
    @ExcelField(name = "收款笔数", orderBy = "2")
    private String count;
    @ExcelField(name = "账单金额", orderBy = "3")
    private String amount;
    @ExcelField(name = "账单生成日期", orderBy = "4")
    private String date;
    @ExcelField(name = "结算周期", orderBy = "5")
    private String cycle;
    @ExcelField(name = "结算状态", orderBy = "6")
    private String status;
}
