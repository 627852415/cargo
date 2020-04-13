package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author liyunhua
 * @Date 2018-09-10 0010
 */
@Getter
@Setter
public class GroupMemberListResp implements Serializable {

    private List<GroupMember> list;

}
