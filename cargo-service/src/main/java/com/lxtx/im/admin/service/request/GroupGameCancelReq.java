package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 取消游戏排队
 *
 * @author liyunhua
 * @date 2018-12-04 0004
 */
@Setter
@Getter
public class GroupGameCancelReq {


    /**
     * 游戏任务id
     */
    private String taskId;

    /**
     * 取消人
     */
    private String cancelerUserId;

    /**
     * 群id
     */
    private String groupId;


}
