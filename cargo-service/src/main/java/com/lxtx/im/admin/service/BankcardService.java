package com.lxtx.im.admin.service;

import org.springframework.ui.Model;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.BankcardListPageReq;
import com.lxtx.im.admin.service.request.OtcBindBankcardNewReq;
import com.lxtx.im.admin.service.request.OtcBindBankcardUpdateReq;

/**
 * 银行卡管理
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
public interface BankcardService {

	BaseResult index(Model model);

	BaseResult listPage(BankcardListPageReq req);

    BaseResult unbind(OtcBindBankcardUpdateReq req);

    BaseResult bind(OtcBindBankcardNewReq req);

}
