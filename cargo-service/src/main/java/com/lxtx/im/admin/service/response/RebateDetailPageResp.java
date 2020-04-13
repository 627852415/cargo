package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class RebateDetailPageResp {

    /**
     * 玩家userid
     */
    @ExcelField(name = "用户账号", orderBy = "2")
   private String playerAccount;

    /**
     * 逗号隔开
     */
    @ExcelField(name = "分佣金额", orderBy = "10")
   private String rebateAmount;

    @ExcelField(name = "用户名", orderBy = "1")
   private String playerUserName;

    @ExcelField(name = "手机号", orderBy = "3")
   private String telephone;


    @ExcelField(name = "游戏id", orderBy = "4")
    private String taskId;

    @ExcelField(name = "群id", orderBy = "5")
    private String groupId;

    /**
     * 群名称
     */
    @ExcelField(name = "群名称", orderBy = "6")
    private String groupName;

    /**
     * 游戏币种名称
     */
    @ExcelField(name = "币种", orderBy = "8")
    private String gameCoinName;

    /**
     * 用户参与该游戏次数
     */
    @ExcelField(name = "参与次数", orderBy = "7")
    private Integer gameJoinTimes;

    /**
     * 该局游戏的总佣金/返点金额（所有用户）
     */
    @ExcelField(name = "分佣池", orderBy = "9")
    private BigDecimal rebateTotalAmount;

    /**
     * 游戏结束时间
     */
    @ExcelField(name = "游戏结束时间", orderBy = "11")
    private String gameEndTime;
}
