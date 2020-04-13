package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.BcbBankCardTypeDeleteReq;
import com.lxtx.im.admin.service.request.BcbBankCardTypeDetailReq;
import com.lxtx.im.admin.service.request.BcbBankCardTypeReq;
import com.lxtx.im.admin.service.request.BcbBankCardTypeSaveReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/2 10:57
 */
public interface BankcardTypeService {

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    BaseResult listPage(BcbBankCardTypeReq req);

    /**
     * 获取所有数据
     *
     * @return
     */
    BaseResult selectAllList();

    /**
     * 保存
     *
     * @param req
     * @return
     */
    BaseResult saveCardType(BcbBankCardTypeSaveReq req);

    /**
     * 上传银行卡图片
     *
     * @param file
     * @return
     */
    BaseResult upload(MultipartFile file) throws IOException;

    /**
     * 根据ID查询对象
     *
     * @param req
     * @return
     */
    BaseResult getById(BcbBankCardTypeDetailReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    BaseResult delete(BcbBankCardTypeDeleteReq req);
}
