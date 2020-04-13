package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AirdropFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 空投外部调用
 * @author: CXM
 * @create: 2018-08-31 09:59
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = AirdropFeignFallbackFactory.class)
public interface AirdropFeign {
    /**
     * 空投列表
     *
     * @param feignAirdropReq
     * @return
     */
    @PostMapping(value = "/airdrop/list")
    BaseResult listPage(FeignAirdropReq feignAirdropReq);

    /**
     * 根据id删除空投
     *
     * @param feignDeleteAirdropReq
     * @return
     */
    @PostMapping(value = "/airdrop/delete")
    BaseResult deleteById(FeignDeleteAirdropReq feignDeleteAirdropReq);

    /**
     * 获取空投相关数据
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/airdrop/relevant/data")
    BaseResult getAirdropData(FeignAirdropToSavePageReq feignReq);

    /**
     * 保存空投
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/airdrop/add")
    BaseResult saveOrEdit(FeignSaveAirdropReq feignReq);

    /**
     * 空投详情
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/airdrop/detail")
    BaseResult detailAirdropList(FeignAirdropDetailReq feignReq);
}
