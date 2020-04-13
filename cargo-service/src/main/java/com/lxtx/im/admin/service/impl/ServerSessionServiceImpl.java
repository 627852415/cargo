package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.ServerSessionService;
import com.lxtx.im.admin.service.response.SessionResp;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/13 15:27
 */
@Service
public class ServerSessionServiceImpl implements ServerSessionService {
    @Override
    public BaseResult getSession(HttpServletRequest request) {
        SessionResp sessionResp = new SessionResp();
        //获取session
        HttpSession session = request.getSession();

        //获取session属性
        Set<String> permsSet = (Set<String>) session.getAttribute("permsSet");
        sessionResp.setPermsSet(permsSet);
        return BaseResult.success(sessionResp);
    }
}
