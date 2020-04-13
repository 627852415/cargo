package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GroupOwnerRebateDetailResp {


    @ExcelField(name = "游戏id", orderBy = "1")
    private String taskId;

    @ExcelField(name = "用户id",orderBy = "2")
    private String playerAccount;

    @ExcelField(name = "用户名",orderBy = "3")
    private String playerUserName;

    @ExcelField(name = "群id", orderBy = "4")
    private String groupId;


    /**
     * 群名称
     */
    @ExcelField(name = "群名称", orderBy = "5")
    private String groupName;

    /**
     * 游戏币种名称
     */
    @ExcelField(name = "币种", orderBy = "6")
    private String gameCoinName;

    /**
     * 该局游戏的总佣金/返点金额（所有用户）
     */
    @ExcelField(name = "分佣池", orderBy = "7")
    private BigDecimal rebateTotalAmount;

    /**
     * 逗号隔开
     */
    @ExcelField(name = "分佣金额", orderBy = "8")
   private String rebateAmount;

    /**
     * 游戏结束时间
     */
    @ExcelField(name = "游戏结束时间", orderBy = "9")
    private String gameEndTime;
}
