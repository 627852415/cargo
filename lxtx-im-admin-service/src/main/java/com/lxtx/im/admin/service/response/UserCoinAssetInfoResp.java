package com.lxtx.im.admin.service.response;

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
public class UserCoinAssetInfoResp {

    private String id;

    private String refId;

    private String walletUserId;

    /**
     * im用户账号
     */
    private String platformUserId;

    private String userName;

    /**
     *  类型【1：提币/转账，2：充值/收款，3：站内转账/好友转账，4：空投，5：红包，6：游戏】
     */
    private Integer type;
    private String typeValue;

    /**
     * 处理前余额
     */
    private BigDecimal preLeftAmount;
    /**
     * 处理前冻结金额
     */
    private BigDecimal preFrozenAmount;
    /**
     * 处理前锁定金额
     */
    private BigDecimal preLockAmount;

    /**
     * 变更余额（+ -）
     */
    private BigDecimal changeLeftAmount;
    /**
     * 变更冻结金额（+ -）
     */
    private BigDecimal changeFrozenAmount;
    /**
     * 变更锁定金额（+ -）
     */
    private BigDecimal changeLockAmount;

    /**
     * 处理后余额
     */
    private BigDecimal suffixLeftAmount;
    /**
     * 处理后冻结金额
     */
    private BigDecimal suffixFrozenAmount;
    /**
     * 处理后锁定金额
     */
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
     * 币种名称
     */
    private String coinName;

    /**
     * 子类型【
     * 提币/转账-> 11：站内转账，12：站内提现，13：站外提现，14：资金归集，15：OTC提款
     * 充值/收款-> 21：站内充值，22：站外充值，23：OTC充值
     * 游戏-> 31：牛牛（庄闲家保存在备注信息），32：21点；
     * 红包-> 41：发红包，42：领红包
     * 】
     */
    private Integer subtype;
    private String subtypeValue;

    /**
     * 状态【1：待处理，2：处理中，3：已提交，4：成功，5:失败】
     */
    private Integer status;
    private String statusValue;

    /**
     * 外部手续费（eg：6X）
     */
    private BigDecimal thirdFee;
    /**
     * 内部手续费
     */
    private BigDecimal innerFee;

    /**
     * 业务平台交易编号/业务平台内唯一编号
     */
    private String transferNum;

    /**
     * 交易说明/备注6X
     */
    private String thirdPartyRemarks;

    /**
     * 转出方钱包地址
     */
    private String fromAddr;
    /**
     * 转入方钱包地址
     */
    private String toAddr;
    /**
     * 接受用户ID
     */
    private String receiverId;
    /**
     * 修改时间
     */
    private Date updateTime;
    private String updateStrTime;

    /**
     * 备注
     */
    private String remarks;
}
