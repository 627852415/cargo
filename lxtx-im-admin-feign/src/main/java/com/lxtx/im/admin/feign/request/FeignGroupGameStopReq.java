package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 结束游戏
 *
 * @author liyunhua
 * @date 2018-12-04 0004
 */
@Setter
@Getter
public class FeignGroupGameStopReq {


    /**
     * 群ID
     */
    private String groupId;

    /**
     * gameTask主键
     */
    private String gid;

    /**
     * 红包id
     */
    private String redPacketId;

    /**
     * 游戏发起人
     */
    private String account;


}
