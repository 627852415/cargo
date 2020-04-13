package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author xumf
 * @date 2019/11/23 11:29
 */
@Setter
@Getter
public class FeignAdminSendListReq extends BasePageReq {

    /**
     * 红包发送 ID
     */
    private String redPacketSendId;
    /**
     * 发送方钱包 ID
     */
    private List<String> userIds;
    /**
     * 接收方用户ID 或者 群 ID
     */
    private List<String> receiverIds;
    /**
     * 红包的类型，0:个人红包，1:群抢红包，2群人均红包
     */
    private Integer type;
    /**
     * 币种名称
     */
    private String coinId;
    /**
     * 0:未领取完，1：已领取完，2：未领取且已过期
     */
    private Integer state;
    /**
     * 开始时间-交易时间
     */
    private Date startCreateTime;
    /**
     * 结束时间-交易时间
     */
    private Date endCreateTime;

    /**
     * 是否柬埔寨演示账号
     */
    private boolean isKHShowAccount;
}
