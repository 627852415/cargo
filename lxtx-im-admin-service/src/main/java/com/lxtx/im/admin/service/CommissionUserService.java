package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CommissionUserDetailReq;
import com.lxtx.im.admin.service.request.CommissionUserListReq;
import com.lxtx.im.admin.service.request.CommissionUserUpdateReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 返佣伙伴业务接口
 *
 * @author xufeifei
 * @since 2020-03-07
 */
public interface CommissionUserService {

    /**
     * 分页返佣伙伴用户
     *
     * @param commissionUserListReq
     * @return
     */
    BaseResult listPage(CommissionUserListReq commissionUserListReq);

    /**
     * 交易流水详情
     *
     * @param commissionUserDetailReq
     * @return
     */
    void detail(CommissionUserDetailReq commissionUserDetailReq, Model model);

    /**
     * 修改用户返佣权限
     * @param  req
     * @return
     */
    BaseResult update(CommissionUserUpdateReq req);

    /**
     * 导出文件锁
     *
     * @param req
     * @param session
     */
    BaseResult downloadLock(CommissionUserListReq req, HttpSession session);

    /**
     * 导出文件
     */
    void downloadList(HttpServletResponse response, HttpSession session, CommissionUserListReq req);

    /**
     * 广告地址修改通知
     *
     * @return
     */
    BaseResult adNotify();

}
