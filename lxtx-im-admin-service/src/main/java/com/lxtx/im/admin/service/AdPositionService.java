package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.AdPositionDetailByIdReq;
import com.lxtx.im.admin.service.request.AdPositionListReq;
import com.lxtx.im.admin.service.request.AdPositionSaveReq;
import org.springframework.ui.Model;

/**
 * 广告位
 *
 * @author xufeifei
 */
public interface AdPositionService {

    /**
     * 保存
     *
     * @param req
     * @return
     */
    BaseResult listPage(AdPositionListReq req);

    BaseResult save(AdPositionSaveReq req);

    void detail(AdPositionDetailByIdReq req, Model model);

    BaseResult deleteById(AdPositionDetailByIdReq req);
}
