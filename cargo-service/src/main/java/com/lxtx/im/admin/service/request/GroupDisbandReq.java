package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 解散群
 *
 * @author liyunhua
 * @date 2018-12-04 0004
 */
@Setter
@Getter
public class GroupDisbandReq {


    /**
     * 群ID
     */
    private String groupId;


    private String account;


}
