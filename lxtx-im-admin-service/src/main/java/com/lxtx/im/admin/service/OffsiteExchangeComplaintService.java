package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.OffsiteArbitrationDetailResp;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @description:  线下汇换投诉接口
* @author:   CXM
* @create:   2019-04-22 15:04
*/
public interface OffsiteExchangeComplaintService {
    /**
     * 获取投诉数据
     *
     * @param req
     * @return
     */
    BaseResult listPage(OffsiteExchangeArbitrationListPageReq req);

    /**
     * 投诉详情
     *
     * @param req
     * @return
     */
    OffsiteArbitrationDetailResp detail(OffsiteExchangeComplaintDetailReq req);

    /**
     * 投诉记录列表
     *
     * @param req
     * @return
     */
    BaseResult saveRecord(OffsiteExchangeComplaintRecordSaveReq req);

    /**
     * 上传凭证
     *
     * @param file
     * @return
     * @throws IOException
     */
    BaseResult uploadCertificate(MultipartFile file) throws IOException;

    /**
     * 投诉处理完成
     *
     * @param req
     * @return
     */
    BaseResult completed(OffsiteExchangeComplaintCompletedReq req);

    BaseResult messageList(SingleHistroyListReq req) throws Exception;

    /**
     * 撤销投诉
     * @param req
     * @return
     */
    BaseResult revocation(OffsiteExchangeComplaintRevocationReq req);
}
