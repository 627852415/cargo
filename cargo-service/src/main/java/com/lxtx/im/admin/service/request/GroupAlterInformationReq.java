package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 修改群私聊权限
 *
 * @author CaiRH
 * @Date 2018-11-23
 */
@Setter
@Getter
public class GroupAlterInformationReq {

    /**
     * 群ID
     */
    private String groupId;
    /**
     * 开启是否查看信息权限设置 0 没有开启，1 有开启
     */
    private Boolean informationFlag;
}
