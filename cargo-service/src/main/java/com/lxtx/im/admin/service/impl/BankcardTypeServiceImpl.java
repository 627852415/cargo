package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.BcbBankcardFeign;
import com.lxtx.im.admin.feign.request.FeignBcbBankCardTypeDeleteReq;
import com.lxtx.im.admin.feign.request.FeignBcbBankCardTypeDetailReq;
import com.lxtx.im.admin.feign.request.FeignBcbBankCardTypeSaveReq;
import com.lxtx.im.admin.feign.request.FeignBcbBankcardTypeReq;
import com.lxtx.im.admin.service.BankcardTypeService;
import com.lxtx.im.admin.service.request.BcbBankCardTypeDeleteReq;
import com.lxtx.im.admin.service.request.BcbBankCardTypeDetailReq;
import com.lxtx.im.admin.service.request.BcbBankCardTypeReq;
import com.lxtx.im.admin.service.request.BcbBankCardTypeSaveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 银行卡卡种管理
 *
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/2 10:58
 */
@Slf4j
@Service
public class BankcardTypeServiceImpl implements BankcardTypeService {

    @Resource
    private BcbBankcardFeign bcbBankcardFeign;
    @Autowired
    private QiNiuUtil qiNiuUtil;

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult listPage(BcbBankCardTypeReq req) {
        FeignBcbBankcardTypeReq feignReq = new FeignBcbBankcardTypeReq();
        BeanUtils.copyProperties(req, feignReq);
        return bcbBankcardFeign.cardTypeList(feignReq);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public BaseResult selectAllList() {
        return bcbBankcardFeign.selectAllList();
    }

    /**
     * 新增或修改卡种
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult saveCardType(BcbBankCardTypeSaveReq req) {
        FeignBcbBankCardTypeSaveReq feignReq = new FeignBcbBankCardTypeSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return bcbBankcardFeign.cardTypeSave(feignReq);
    }

    /**
     * logo上传
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @Override
    public BaseResult upload(MultipartFile multipartFile) throws IOException {
        //String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_OTHER.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_OTHER);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
    }

    /**
     * 根据ID获取详情
     * param req
     *
     * @return
     */
    @Override
    public BaseResult getById(BcbBankCardTypeDetailReq req) {
        FeignBcbBankCardTypeDetailReq feignReq = new FeignBcbBankCardTypeDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        return bcbBankcardFeign.getById(feignReq);
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult delete(BcbBankCardTypeDeleteReq req) {
        FeignBcbBankCardTypeDeleteReq delReq = new FeignBcbBankCardTypeDeleteReq();
        BeanUtils.copyProperties(req, delReq);
        return bcbBankcardFeign.delete(delReq);
    }
}
