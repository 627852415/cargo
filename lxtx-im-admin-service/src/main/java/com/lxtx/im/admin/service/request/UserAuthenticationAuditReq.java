package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户身份认证审核类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class UserAuthenticationAuditReq {

    /**
     * Id
     */
    private String id;

    /**
     * 认证状态【1:未认证、2:待审核、3:已认证、4:已拒绝】
     */
    private Integer certificateStatus;

    /**
     * 拒绝理由
     */
    private String rejectReason;

}
