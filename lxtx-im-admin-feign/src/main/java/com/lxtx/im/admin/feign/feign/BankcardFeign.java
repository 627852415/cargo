package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.BankcardFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignBankcardListPageReq;
import com.lxtx.im.admin.feign.request.FeignOtcBindBankcardNewReq;
import com.lxtx.im.admin.feign.request.FeignOtcBindBankcardUpdateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 银行卡
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = BankcardFallbackFactory.class)
public interface BankcardFeign {

    /**
     * 银行卡列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/otc/bankcard/listPage")
    BaseResult listPage(FeignBankcardListPageReq req);

    /**
     * 更新银行卡信息（解绑、切换设置默认）
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/otc/bankcard/update")
    BaseResult bindBankcardUpdate(FeignOtcBindBankcardUpdateReq feignReq);

    @PostMapping(value = "/otc/bankcard/bind/new/admin")
    BaseResult bind(FeignOtcBindBankcardNewReq feignReq);

}
