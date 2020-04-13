package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import com.lxtx.im.admin.feign.request.BasePageReq;

import java.math.BigDecimal;

/**
 * 扫码付款扫一扫请求币种信息
 *
 * @author CaiRH
 * @since 2019-03-08
 */
@Getter
@Setter
public class ScanPayReq extends BasePageReq {
    /**
     * 币种ID
     */
    @NotBlank(message = "缺少币种信息")
    private String coinId;

    /**
     * 平台用户账号
     */
    @NotBlank(message = "平台用户账号不能为空")
    private String account;

    /**
     * 收款金额
     */
    private BigDecimal receiptAmount;
}
