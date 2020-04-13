package com.lxtx.im.admin.service;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.WithdrawConfigDeleteReq;
import com.lxtx.im.admin.service.request.WithdrawConfigPageReq;
import com.lxtx.im.admin.service.request.WithdrawConfigSaveOrUpdateReq;

/**
 * @author Czh
 * Date: 2019-06-25 14:40
 */
public interface OtcWithdrawConfigService {

    /**
     * 功能描述: 获取OTC提现配置
     * 
     * @param: id
     * @return: 
     * @author: Czh
     * @date: 2019-06-25 16:54
     */
    Object selectById(String id);

    /**
     * 功能描述: 保存OTC提款配置
     *w
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    BaseResult withdrawConfigSaveOrUpdate(WithdrawConfigSaveOrUpdateReq req);

    /**
     * 功能描述: 获取OTC提款配置
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    BaseResult withdrawConfigListPage(WithdrawConfigPageReq req);

    /**
     * 功能描述: 删除OTC提款配置
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    BaseResult withdrawConfigDelete(WithdrawConfigDeleteReq req);

    /**
     * 功能描述: 启用OTC提款配置
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    BaseResult withdrawConfigEnable(WithdrawConfigDeleteReq req);
}
