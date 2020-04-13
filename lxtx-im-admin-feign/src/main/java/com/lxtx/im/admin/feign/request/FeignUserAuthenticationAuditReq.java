package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Getter
@Setter
public class FeignUserAuthenticationAuditReq {

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
