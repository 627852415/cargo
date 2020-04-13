package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 资产流水表
 *
 * @author tangdy
 * @since 2018-12-25
 */
@Getter
@Setter
public class UserCoinAssetListResp {

    private String id;

    @ExcelField(name = "钱包用户ID", orderBy = "1")
    private String walletUserId;

    /**
     * im用户账号
     */
    private String platformUserId;

    @ExcelField(name = "用户名", orderBy = "2")
    private String userName;

    /**
     * 币种名称
     */
    @ExcelField(name = "币种名称", orderBy = "3")
    private String coinName;

    /**
     *  类型【1：提币/转账，2：充值/收款，3：站内转账/好友转账，4：空投，5：红包，6：游戏】
     */
    private Integer type;
    @ExcelField(name = "类型", orderBy = "4")
    private String typeValue;

    /**
     * 子类型【
     * 提币/转账-> 11：站内转账，12：站内提现，13：站外提现，14：资金归集，15：OTC提款
     * 充值/收款-> 21：站内充值，22：站外充值，23：OTC充值
     * 游戏-> 31：牛牛（庄闲家保存在备注信息），32：21点；
     * 红包-> 41：发红包，42：领红包
     * 】
     */
    private Integer subtype;
    @ExcelField(name = "子类型", orderBy = "5")
    private String subtypeValue;

    @ExcelField(name = "状态", orderBy = "6")
    private String statusValue;

    /**
     * 处理前余额
     */
    @ExcelField(name = "处理前余额", orderBy = "7")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal preLeftAmount;

    /**
     * 变更余额（+ -）
     */
    @ExcelField(name = "变更余额", orderBy = "8")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal changeLeftAmount;

    /**
     * 处理后余额
     */
    @ExcelField(name = "处理后余额", orderBy = "9")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal suffixLeftAmount;
    /**
     * 处理前冻结金额
     */
    @ExcelField(name = "处理前冻结金额", orderBy = "10")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal preFrozenAmount;

    /**
     * 变更冻结金额（+ -）
     */
    @ExcelField(name = "变更冻结金额", orderBy = "11")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal changeFrozenAmount;

    /**
     * 处理后冻结金额
     */
    @ExcelField(name = "处理后冻结金额", orderBy = "12")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal suffixFrozenAmount;
    /**
     * 处理前锁定金额
     */
    @ExcelField(name = "处理前锁定金额", orderBy = "13")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal preLockAmount;

    /**
     * 变更锁定金额（+ -）
     */
    @ExcelField(name = "变更锁定金额", orderBy = "14")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal changeLockAmount;

    /**
     * 处理后锁定金额
     */
    @ExcelField(name = "处理后锁定金额", orderBy = "15")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal suffixLockAmount;

    //----------------------------------------
    /**
     * 钱包用户ID
     */
    private String userId;

    /**
     * 币种ID
     */
    private String coinId;

    /**
     * 状态【1：待处理，2：处理中，3：已提交，4：成功，5:失败】
     */
    private Integer status;

    /**
     * 外部手续费（eg：6X）
     */
    @ExcelField(name = "外部手续费", orderBy = "16")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal thirdFee;
    /**
     * 内部手续费
     */
    @ExcelField(name = "内部手续费", orderBy = "17")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal innerFee;

    /**
     * 业务平台交易编号/业务平台内唯一编号
     */
    @ExcelField(name = "业务平台交易编号", orderBy = "18")
    private String transferNum;

    /**
     * 交易说明/备注6X
     */
    @ExcelField(name = "交易说明", orderBy = "19")
    private String thirdPartyRemarks;

    /**
     * 转出方钱包地址
     */
    @ExcelField(name = "转出方钱包地址", orderBy = "20")
    private String fromAddr;
    /**
     * 转入方钱包地址
     */
    @ExcelField(name = "转入方钱包地址", orderBy = "21")
    private String toAddr;
    /**
     * 接受用户ID
     */
    private String receiverId;

    /**
     * 修改时间
     */
    @ExcelField(name = "修改时间", orderBy = "22")
    private Date updateTime;

    /**
     * 备注
     */
    @ExcelField(name = "备注", orderBy = "23")
    private String remarks;
}
