package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WalletOtcOrderFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangdy
 * @Date 2018-08-029
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WalletOtcOrderFallbackFactory.class)
public interface WalletOtcFeign {



    /**
     *  otc订单列表
     * @param feignReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @auther LiuLP
     * @date 2018/10/25 0025
     */
    @PostMapping(value = "/otc/order/list")
    BaseResult listPage(FeignQueryOtcOrderListReq feignReq);

    /**
     *  审核订单
     * @param feignReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @auther LiuLP
     * @date 2018/10/25 0025
     */
    @PostMapping(value = "/otc/order/update/check/state")
    BaseResult updateCheckState(FeignUpdateOrderCheckStateReq feignReq);

    /**
     * 功能描述: 获取OTC提现配置
     * 
     * @param: feignReq
     * @return: 
     * @author: Czh
     * @date: 2019-06-25 16:58
     */
    @PostMapping(value = "/otc/withdraw/config/one")
    BaseResult withdrawConfigOne(FeignWithdrawConfigOneReq feignReq);

    /**
     * 功能描述: 保存OTC提款配置
     *w
     * @param: feignReq
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @PostMapping("/otc/withdraw/config/saveOrUpdate")
    BaseResult withdrawConfigSaveOrUpdate(FeignWithdrawConfigSaveOrUpdateReq feignReq);

    /**
     * 功能描述: 获取OTC提款配置
     *
     * @param: feignReq
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @PostMapping("/otc/withdraw/config/listPage")
    BaseResult withdrawConfigListPage(FeignWithdrawConfigPageReq feignReq);

    /**
     * 功能描述: 删除OTC提款配置
     *
     * @param: feignReq
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @PostMapping("/otc/withdraw/config/delete")
    BaseResult withdrawConfigDelete(FeignWithdrawConfigOneReq feignReq);

    /**
     * 功能描述: 启用OTC提款配置
     *
     * @param: feignReq
     * @return:
     * @author: Czh
     * @date: 2019-06-25 15:50
     */
    @PostMapping("/otc/withdraw/config/enable")
    BaseResult withdrawConfigEnable(FeignWithdrawConfigOneReq feignReq);
}
