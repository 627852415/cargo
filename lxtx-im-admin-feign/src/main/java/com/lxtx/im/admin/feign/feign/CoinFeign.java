package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.CoinFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 币种外部请求
 *
 * @author CaiRH
 * @since 2018-08-29
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = CoinFallbackFactory.class)
public interface CoinFeign {

    /**
     * 异常处理记录
     *
     * @param feignCoinReq
     * @return
     */
    @PostMapping(value = "/coin/list")
    BaseResult listPage(FeignCoinReq feignCoinReq);

    @PostMapping(value = "/coin/list/legal")
    BaseResult listPageLegal(FeignCoinReq feignCoinReq);



    /**
     * 获取币种详情
     *
     * @param feignCoinDetailReq
     * @return
     */
    @PostMapping(value = "/coin/detail")
    BaseResult selectOne(FeignCoinDetailReq feignCoinDetailReq);

    /**
     * 保存币种信息
     *
     * @param saveReq
     * @return
     */
    @PostMapping(value = "/coin/save")
    BaseResult save(FeignCoinSaveReq saveReq);

    /**
     * 删除币种信息
     *
     * @param deleteReq
     * @return
     */
    @PostMapping(value = "/coin/delete")
    BaseResult delete(FeignCoinDeleteReq deleteReq);

    /**
     * 获取币种手续费
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/coin/fee")
    BaseResult obtainCoinFee(FeignCoinFeeReq req);

    /**
     * 获取所有显示的币种
     * @param req
     * @return
     */
    @PostMapping(value = "/coin/listAll")
    BaseResult listAll(FeignCoinReq req);

    @PostMapping(value = "/coin/listAll/legal")
    BaseResult listAllLegal(FeignCoinReq req);
}
