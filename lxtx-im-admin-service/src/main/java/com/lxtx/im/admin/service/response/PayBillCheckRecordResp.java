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
public class PayBillCheckRecordResp {

    @ExcelField(name = "用户名", orderBy = "1")
    private String userName;

    @ExcelField(name = "用户ID", orderBy = "2")
    private String userId;

    private String walletUserId;

    private String platformUserId;

    @ExcelField(name = "地区", orderBy = "3")
    private String area;

    @ExcelField(name = "手机号", orderBy = "4")
    private String phoneNum;

    @ExcelField(name = "创建时间", orderBy = "5")
    private String createTime;

    @ExcelField(name = "结算周期", orderBy = "6")
    private String cycle;

    @ExcelField(name = "结算手续费", orderBy = "7")
    private String fee;
}
