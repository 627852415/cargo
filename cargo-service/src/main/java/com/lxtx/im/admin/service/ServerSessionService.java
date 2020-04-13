package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/13 15:25
 */
public interface ServerSessionService {
    BaseResult getSession(HttpServletRequest request);
}
