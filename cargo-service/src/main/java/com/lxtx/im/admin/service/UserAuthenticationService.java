package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.UserAuthenticationAuditReq;
import com.lxtx.im.admin.service.request.UserAuthenticationDelReq;
import com.lxtx.im.admin.service.request.UserAuthenticationDetailReq;
import com.lxtx.im.admin.service.request.UserAuthenticationEditReq;
import com.lxtx.im.admin.service.request.UserAuthenticationPageReq;
import com.lxtx.im.admin.service.request.UserAuthenticationUpdateStatusReq;
import org.springframework.ui.Model;

/**
 * <p>
 * 用户身份认证服务类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
public interface UserAuthenticationService {

    /**
     * 用户身份认证列表
     * @param req
     * @return
     */
    BaseResult page(UserAuthenticationPageReq req);

    /**
     * 更新用户身份认证状态
     * @param req
     * @return
     */
    BaseResult updateStatus(UserAuthenticationUpdateStatusReq req);

    /**
     * 更新用户身份认证状态
     * @param req
     * @return
     */
    BaseResult audit(UserAuthenticationAuditReq req);

    /**
     * 更新用户身份认证信息
     * @param req
     * @return
     */
    BaseResult edit(UserAuthenticationEditReq req);

    /**
     * 用户身份认证详情
     * @param req
     * @param model
     * @return
     */
    String detail(UserAuthenticationDetailReq req, Model model);

    /**
     * 删除用户身份认证
     * @param req
     * @return
     */
    BaseResult del(UserAuthenticationDelReq req);
}
