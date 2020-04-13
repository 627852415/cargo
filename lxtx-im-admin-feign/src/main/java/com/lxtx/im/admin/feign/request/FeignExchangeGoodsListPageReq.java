package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 */
@Getter
@Setter
public class FeignExchangeGoodsListPageReq extends BasePageReq {

    /**
     * 用户Id集合
     */
    private List<String> account;

    /**
     * 币对Id
     */
    private String waveRateId;

    /**
     * 统计时间
     */
    private Date startDateTime;

    /**
     * 统计时间
     */
    private Date endDateTime;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isShowAccount;
}
