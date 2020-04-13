package com.lxtx.im.admin.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.CommissionFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAdminCommissionUserUpdateReq;
import com.lxtx.im.admin.feign.request.FeignCommissionConfigAddToStringReq;
import com.lxtx.im.admin.feign.request.FeignCommissionConfigListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraConfigAddToStringReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistAddReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistReq;
import com.lxtx.im.admin.feign.request.FeignCommissionLevelAddReq;
import com.lxtx.im.admin.feign.request.FeignCommissionLevelByIdReq;
import com.lxtx.im.admin.feign.request.FeignCommissionLevelListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionOrderDetailReq;
import com.lxtx.im.admin.feign.request.FeignCommissionOrderListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionUserDetailReq;
import com.lxtx.im.admin.feign.request.FeignCommissionUserListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionUserUpdateReq;
import com.lxtx.im.admin.feign.request.FeignCommissionWhitelistReq;

/**
 * 返佣伙伴
 *
 * @author xufeifei
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = CommissionFeignFallbackFactory.class)
public interface CommissionFeign {

    /**
     * 返佣伙伴列表
     *
     * @param req
     * @return
     */
    @PostMapping("/commission/user/listPage")
    BaseResult listPage(FeignCommissionUserListReq req);

    /**
     * 返佣伙伴详情
     *
     * @param req
     * @return
     */
    @PostMapping("/commission/user/detail")
    BaseResult detail(FeignCommissionUserDetailReq req);

    /**
     * 新增分佣级别
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionLevel/level/add")
    BaseResult levelAdd(FeignCommissionLevelAddReq req);

    /**
     * 获取分佣级别
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionLevel/level/getLevel")
    BaseResult getLevel(FeignCommissionLevelByIdReq req);

    /**
     * 获取分佣列表
     *
     * @param
     * @return
     */
    @PostMapping("/commissionLevel/level/all")
    BaseResult levelAll();

    /**
     * 返佣伙伴详情
     *
     * @param req
     * @return
     */
    @PostMapping("/commission/user/update")
    BaseResult update(FeignCommissionUserUpdateReq req);

    /**
     * 返佣伙伴详情
     *
     * @param req
     * @return
     */
    @PostMapping("/commission/user/commission/user")
    BaseResult downloadList(FeignCommissionUserListReq req);

    /**
     * 佣级别列表
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionLevel/level/list")
    BaseResult levelList(FeignCommissionLevelListReq req);

    /**
     * 新增分佣设置
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionConfig/config/add")
    BaseResult configAdd(FeignCommissionConfigAddToStringReq req);

    /**
     * 分佣设置列表
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionConfig/config/list")
    BaseResult configList(FeignCommissionConfigListReq req);

    /**
     * 分佣订单列表
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionOrder/order/list")
    BaseResult orderList(FeignCommissionOrderListReq req);

    /**
     * 返佣订单详情
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionOrder/order/detail")
    BaseResult orderDetail(FeignCommissionOrderDetailReq req);

    /**
     * 广告地址修改通知
     *
     * @return
     */
    @PostMapping("/commission/user/notify")
    BaseResult adNotify();

    /**
     * 额外分佣白名单列表
     * @param req
     * @return
     */
    @PostMapping("/commissionExtraWhitelist/page/list")
    BaseResult selectExtraWhitelistPage(FeignCommissionExtraWhitelistListReq req);

    /**
     * 额外分佣白名单国家列表
     * @param req
     * @return
     */
    @PostMapping("/commissionExtraWhitelist/global/list")
    BaseResult selectWhitelist(FeignCommissionWhitelistReq req);

    /**
     * 额外分佣白名单国家列表
     * @param req
     * @return
     */
    @PostMapping("/commissionExtraWhitelist/add")
    BaseResult addExtraWhitelist(FeignCommissionExtraWhitelistAddReq req);

    /**
     * 额外分佣移出白名单
     * @param req
     * @return
     */
    @PostMapping("/commissionExtraWhitelist/del")
    BaseResult delExtraWhitelist(FeignCommissionExtraWhitelistReq req);

    /**
     * 地区分佣比例编辑列表
     * @param req
     * @return
     */
    @PostMapping("/commissionExtraWhitelist/config/list")
    BaseResult selectExtraConfigList(FeignCommissionExtraWhitelistReq req);

    /**
     * 地区分佣比例编辑保存
     * @param req
     * @return
     */
    @PostMapping("/commissionExtraConfig/add")
    BaseResult addExtraConfig(FeignCommissionExtraConfigAddToStringReq req);
    
    /**
     * 返佣伙伴等级申请记录列表
     *
     * @param req
     * @return
     */
    @PostMapping("/commissionUser/getCommissionUserUpdateList")
    BaseResult getCommissionUserUpdateList(FeignAdminCommissionUserUpdateReq req);
}
