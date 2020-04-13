package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.DictCoinMaxListPageReq;
import com.lxtx.im.admin.service.request.DictDeleteReq;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.DictListPageReq;
import com.lxtx.im.admin.service.request.DictModifyValueReq;
import com.lxtx.im.admin.service.request.DictSaveReq;
import com.lxtx.im.admin.service.request.NoticeSaveReq;

/**
* @description:  字典管理
* @author:   CXM
* @create:   2018-10-12 14:53
*/
public interface DictService {


    String getKhUserValue();

    /**
     * 获取字典管理列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(DictListPageReq req);

    /**
     * 删除字典
     *
     * @param req
     * @return
     */
    BaseResult delete(DictDeleteReq req);

    /**
     * 保存或更新字典
     *
     * @param req
     * @return
     */
    BaseResult saveOrUpdate(DictSaveReq req);

    /**
     * 根据id获取字典信息
     *
     * @param req
     * @return
     */
    BaseResult info(DictInfoReq req);

    /**
     * 保存币种交易监控通知信息
     *
     * @param req
     * @return
     */
    BaseResult saveNotice(NoticeSaveReq req);

    /***
     *获取交易通知信息
     *
     * @return
     */
    BaseResult transactionNoticeInfo();

    /***
     *获取添加币种通知信息
     *
     * @return
     */
    BaseResult addCoinNoticeInfo();

    /**
     * 获取币种最大交易列表
     *
     * @param req
     * @return
     */
    BaseResult listPageCoinMax(DictCoinMaxListPageReq req);
    
    /**
     * 根据域和ikey获取value
     *
     * @param domain
     * @param ikey
     * @return
     */
    String getDictValue(String domain, String ikey);
    
    /**
     * 根据domain和key修改对应的值
     * @param req
     * @return
     */
    BaseResult modifyValueByDomainAndKey(DictModifyValueReq req);
    /**
     * 获取特殊国家列表
     * @param
     * @return
     */
    String[] getDictArrayValue(String domain, String ikey);
}
