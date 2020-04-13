package com.lxtx.im.admin.service.response;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 群组表
 * </p>
 *
 * @author liboyan
 * @since 2018-08-14
 */
@Data
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  主键
     */

    private String groupId;

    private String yxGroupId;

    /**
     * 分类（暂未使用）
     */
    private String category;
    /**
     * 创建者用户账号(account)
     */
    private String founder;
    /**
     * 名称
     */
    private String name;
    /**
     * 群组简介
     */
    private String summary;
    /**
     * 群头像路径
     */
    private String groupIconPath;
    /**
     * 是否开启互相添加好友设置
     * 0 false 不开启，1 true 开启。
     */
    private Boolean informationFlag;



}
