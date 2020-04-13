package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;

/**
 * BCB银行卡
 *
 * @author : CaiRH
 * @since : 2019-04-23
 */
public interface BcbBankcardService {
    /**
     * 获取银行卡列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(BcbBankcardListPageReq req);

    BaseResult audit(BcbBankAuditReq req);

    BaseResult applyAuditDetail(BcbBankApplyDetailReq req);

    BaseResult save(BcbBankCardSaveReq req);

    BaseResult cardList(BcbBankCardNumberPageReq req);

    BaseResult cardAdd(BcbBankCardNumReq req);

    BaseResult getById(String id);

}
