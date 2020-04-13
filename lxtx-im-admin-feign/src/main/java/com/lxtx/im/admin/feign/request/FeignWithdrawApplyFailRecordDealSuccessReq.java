package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 提现申请失败记录处理成功请求参数
 *
 * @author CaiRH
 * @since 2019-06-04
 */
@Setter
@Getter
public class FeignWithdrawApplyFailRecordDealSuccessReq {
    /**
     * 记录ID
     */
    @NotBlank(message = "记录ID不能为空")
    private String recordId;

    /**
     * 业务平台交易编号/业务平台内唯一编号
     */
    private String transferNum;

}
