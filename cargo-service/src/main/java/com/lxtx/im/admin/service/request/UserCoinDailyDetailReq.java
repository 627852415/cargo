package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 快照详情请求参数
 *
 * @author CaiRH
 * @since 2019-05-31
 */
@Setter
@Getter
public class UserCoinDailyDetailReq extends BasePageReq {
    /**
     * 币种ID
     */
    private String coinId;

    private String queryCondition;

    /**
     * 快照日期
     */
    private Long recordsDate;
}
