package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.UserCoinDailyListFallBackFactory;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailyDetailReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailyListDownReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailyListReq;
import com.lxtx.im.admin.feign.request.FeignUserCoinDailySnapshotRebuildReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 快照管理
 *
 * @author CaiRH
 * @since 2019-05-31
*/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = UserCoinDailyListFallBackFactory.class)
public interface UserCoinDailyListFeign {

    @PostMapping(value = "/admin/user/coin/daily/list/page")
    BaseResult listPage(FeignUserCoinDailyListReq listPageReq);

    @PostMapping(value = "/admin/user/coin/daily/detail")
    BaseResult detail(FeignUserCoinDailyDetailReq feignReq);

    @PostMapping(value = "/admin/user/coin/daily/excel")
    BaseResult createDetailExcel(FeignUserCoinDailyDetailReq feignReq);

    @PostMapping(value = "/admin/user/coin/daily/list/down")
    BaseResult downData(FeignUserCoinDailyListDownReq feignReq);

    @PostMapping(value = "/daily/assets/snapshot/rebuild")
    BaseResult rebuildSnapshot(FeignUserCoinDailySnapshotRebuildReq feignReq);

}
