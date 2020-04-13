package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * 资金交易流水返回信息
 *
 * @author CaiRH
 * @since 2018-09-27
 */
@Getter
@Setter
public class CapitalResp {

    /**
     * 交易流水ID
     */
    private String id;
    /**
     * 交易状态【1：待处理，2：处理中，3：已提交，4：成功，5:失败，6：部分成功】
     */
    private Integer status;
    /**
     * 交易子类型【1：转账，2：收款，3：好友转账，5：红包发送，6：接收红包，7：niuniu庄家，8：niuniu玩家，10：21点游戏（包括以后的新游戏），11：平台提款，12：OTC提款，13：OTC充值，14：游戏分佣，15：闪兑，16：游戏群主返佣，17：游戏下级返佣】
     */
    private Integer type;

    /**
     * 操作表ID
     */
    private String refId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 交易说明/备注6X
     */
    private String thirdPartyRemarks;

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 币种图标地址
     */
    private String icoUrl;

    /**
     * 用户ID
     */
    @ExcelField(name = "用户ID", orderBy = "1")
    private String userId;
    /**
     * 用户名称
     */
    @ExcelField(name = "用户名称", orderBy = "2")
    private String userName;

    @ExcelField(name = "类型", orderBy = "3")
    private String typeValue;
    @ExcelField(name = "游戏类型", orderBy = "4")
    private String gameTypeValue;
    @ExcelField(name = "状态", orderBy = "5")
    private String statusValue;

    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "6")
    private String coinName;

    /**
     * 数量
     */
    @ExcelField(name = "交易数量", orderBy = "7")
    private String amount;

    /**
     * 手续费
     */
    @ExcelField(name = "手续费", orderBy = "8")
    private String fee;

    /**
     * 转出方钱包地址地址
     */
    @ExcelField(name = "转出方", orderBy = "9")
    private String fromAddr;

    /**
     * 转入方钱包地址
     */
    @ExcelField(name = "转入方", orderBy = "10")
    private String toAddr;

    /**
     * 业务平台交易编号/业务平台内唯一编号
     */
    @ExcelField(name = "业务平台交易编号", orderBy = "11")
    private String transferNum;

    /**
     * 平台用户ID
     */
    private String platformUserId;
    /**
     * 创建时间
     */
    @ExcelField(name = "交易时间", orderBy = "12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelField(name = "更新时间", orderBy = "13")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 是否是退还类型--红包
     */
    private Boolean reject;

    /**
     * 是否是群红包--红包
     */
    private Boolean groupRedPacket;

    /**
     * 退款数量--红包
     */
    private String refundAmount;

    /**
     * 输赢状态, 游戏用
     *
     * @since 2018-11-30
     */
    private Boolean isWin;

    /**
     * 交易总额（otc提款）
     */
    private String totalMoney;
    /**
     * 银行卡号（otc提款）
     */
    private String account;

    /**
     * 游戏类型
     *
     * @since 2018-12-25
     */
    private Integer gameType;
    /**
     * 游戏奖励金额
     */
    private String rewardAmount;
    /**
     * 扫雷游戏金额（交易流水用到）
     */
    private String gameAmount;

    // ---------------------------- 闪兑交易账单相关 ----------------------------
    // 含fee，coinName，icoUrl，remarks，createTime，updateTime
    /**
     * 支付的币种
     */
    private String payCoin;
    /**
     * 意向支付的数量
     */
    private String payAmount;
    /**
     * 获得的币种
     */
    private String gotCoin;
    /**
     * 预期获得的数量
     */
    private String gotAmount;

    /**
     * 实际支付的数量(最终完成状态下有值)
     */
    private String realPayAmount;
    /**
     * 实际获得的数量,已扣除手续费(最终完成状态下有值)
     */
    private String realGotAmount;
    /**
     * 实际的兑换率(最终完成状态下有值)
     */
    private String realRate;

    /**
     * 解冻币种数量（成功或交易部分成功或失败等情况时若需要解冻则返回）
     */
    private String thawAmount;

    /**
     * 订单编号
     */
    private String orderNo;
    // ---------------------------- 闪兑交易账单相关 ----------------------------
}
