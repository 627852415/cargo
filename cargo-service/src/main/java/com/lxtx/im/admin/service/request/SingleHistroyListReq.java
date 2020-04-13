package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-05-24 17:33
 * @Description
 */
@Data
public class SingleHistroyListReq {

    /**
     * 搜索结果的条数限制
     */
    private Integer limit = 1000;
    /**
     * 搜索日期
     */
    @NotBlank(message ="searchDate 参数不能为空")
    private String searchDate;

    /**
     * 用户账号
     */
    @NotBlank(message ="userAccount 参数不能为空")
    private String userAccount;

    /**
     * 订单号
     */
    @NotBlank(message ="orderId 参数不能为空")
    private String orderId;
}
