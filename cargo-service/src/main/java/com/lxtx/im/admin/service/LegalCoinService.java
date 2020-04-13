package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.LegalCoinDelReq;
import com.lxtx.im.admin.service.request.LegalCoinListPageReq;
import com.lxtx.im.admin.service.request.LegalCoinSaveReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * 法币管理
 *
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
public interface LegalCoinService {

    BaseResult listPage(LegalCoinListPageReq req);

    BaseResult save(LegalCoinSaveReq req, HttpSession session);

    BaseResult del(LegalCoinDelReq req);

    BaseResult detail(LegalCoinSaveReq legalCoinSaveReq, Model model);

    void add(Model model);
}
