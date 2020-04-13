package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.BcbBankcardFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * BCB银行卡
 *
 * @author : CaiRH
 * @since : 2019-04-23
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = BcbBankcardFeignFallbackFactory.class)
public interface BcbBankcardFeign {
    /**
     * 银行卡列表
     *
     * @param feign
     * @return
     */
    @PostMapping(value = "/admin/bcb/bank/apply/list")
    BaseResult listPage(FeignBcbBankcardListPageReq feign);

    @PostMapping("/admin/bcb/bank/card/audit")
    BaseResult audit(FeignBcbBankAuditReq req);

    @PostMapping("/admin/bcb/bank/audit/detail")
    BaseResult applyAuditDetail(FeignBcbBankApplyDetailReq req);

    @PostMapping("/admin/bcb/bank/card/list")
    BaseResult cardList(@Valid @RequestBody FeignBcbBankCardNumberPageReq req);

    @PostMapping("/admin/bcb/bank/card/add")
    BaseResult cardAdd(@Valid @RequestBody FeignBcbBankCardNumReq req);

    @PostMapping("/admin/bcb/bank/card/save")
    BaseResult cardSave(@Valid @RequestBody FeignBcbBankCardSaveReq req);

    @PostMapping("/admin/bcb/bank/card/getById")
    BaseResult getCardNumById(@Valid @RequestBody FeignBcbBankCardNumDetailReq req);

    /**
     * 分页查询卡种列表
     *
     * @param req
     * @return
     */
    @PostMapping("/bcb/bank/card/type/list")
    BaseResult cardTypeList(@Valid @RequestBody FeignBcbBankcardTypeReq req);

    /**
     * 查询所有卡种
     *
     * @return
     */
    @PostMapping(value = "/bcb/bank/card/logo/list")
    BaseResult selectAllList();

    /**
     * 保存或修改卡种
     *
     * @param req
     * @return
     */
    @PostMapping("/bcb/bank/card/type/save")
    BaseResult cardTypeSave(@Valid @RequestBody FeignBcbBankCardTypeSaveReq req);

    /**
     * 根据ID查询对象
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/bcb/bank/card/type/getById")
    BaseResult getById(FeignBcbBankCardTypeDetailReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/bcb/bank/card/type/delete")
    BaseResult delete(FeignBcbBankCardTypeDeleteReq req);

}
