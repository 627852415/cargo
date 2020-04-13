package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.DictFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @description:  字典管理
* @author:   CXM
* @create:   2018-10-12 14:52
*/
@FeignClient(value = "lxtx-im-config-client", fallbackFactory = DictFeignFallbackFactory.class)
public interface DictFeign {

    /**
     * 字典管理列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/dict/list")
    BaseResult listPage(FeignDictListPageReq req);
    /**
     * 删除字典
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/dict/delete")
    BaseResult delete(FeignDictDeleteReq req);
    /**
     * 保存或更新字典
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/dict/save")
    BaseResult saveOrUpdate(FeignDictSaveReq req);

    /**
     * 根据id获取字典信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/dict/info")
    BaseResult info(FeignDictInfoReq req);

    /**
     * 根据id获取字典信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/dict/notice/save")
    BaseResult saveNotice(FeignNoticeSaveReq req);

    /**
     * 获取交易通知信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/dict/transaction/notice/info")
    BaseResult transactionNoticeInfo(FeignNoticeInfoReq req);
    
    /**
     * 
     * @param dictGetValueReq
     * @return
     */
    @PostMapping("/dict/value")
	BaseResult getDictValue(@RequestBody FeignDictGetValueReq dictGetValueReq);
    
    /**
     * 根据domain和key修改对应的值
     * @return
     */
    @PostMapping("/dict/modifyValueByDomainAndKey")
    public BaseResult modifyValueByDomainAndKey(FeignDictModifyValueReq feignDictModifyValueReq);
}