package com.lxtx.im.admin.service.response;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 群组成员表
 * </p>
 *
 * @author liboyan
 * @since 2018-08-14
 */
@Data
public class GroupMember implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     *  主键
     */
    private String gid;
    /**
     * 成员用户账户
     */
    private String account;
    /**
     * 群组ID
     */
    private String groupId;
    /**
     * 成员身份标识, 0:普通成员：1群主
     */
    private String host;
    /**
     * 聊天会话窗口置顶【默认false：关闭；true：开启】
     */
    private Boolean topFlag;
    /**
     * 是否免打扰【默认false：关闭；true：开启】
     */
    private Boolean botherFlag;
    /**
     * 成员昵称
     */
    private String memberNickName;

}
