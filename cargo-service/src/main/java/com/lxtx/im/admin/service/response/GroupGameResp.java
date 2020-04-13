package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.dao.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 群游戏
 *
 * @Author: liyunhua
 * @Date: 2019/2/18
 */
@Getter
@Setter
public class GroupGameResp extends BaseModel {

    /**
     * 主键
     * */
    private String gid;

    /**
     * 群id
     */
    private String groupId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群主帐号
     */
    private String founder;

    /**
     * 游戏类型
     */
    private Integer gameType;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 游戏发起人帐号
     * */
    private String account;

    /**
     * 参与人数
     */
    private String playerNum;

    /**
     * 庄家名称
     */
    private String bankerName;

    /**
     * 游戏模式
     */
    private String mode;

    /**
     * 游戏模式名称
     */
    private String modeName;

    /**
     * 游戏状态
     */
    private Integer state;


    /**
     * 游戏状态名称
     */
    private String stateName;

    /**
     * 红包id
     */
    private String redPacketId;

    /**
     * 红包币种Id
     */
    private String redPacketCoinId;

    /**
     * 红包币种
     */
    private String redPacketCoin;

    /**
     * 红包金额
     */
    private String redPacketAmount;

    /**
     * 红包的手续费
     */
    private String redPacketFee;

    /**
     * 游戏币种Id
     */
    private String gameCoinId;

    /**
     * 游戏币种
     */
    private String gameCoin;

    /**
     * 游戏底注
     */
    private String gameAnte;

    /**
     * 游戏总金额
     */
    private String gameTotalAmount;

    /**
     * 游戏持续时间 (单位：秒)
     */
    private Integer burningTime;

    /**
     * 倍数
     */
    private String multiples;


}
