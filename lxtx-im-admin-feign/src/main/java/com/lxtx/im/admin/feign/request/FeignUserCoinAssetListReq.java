package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @description: 资金流水列表请求参数
 * @author: CXM
 * @create: 2018-12-19 14:05
 */
@Setter
@Getter
public class FeignUserCoinAssetListReq extends BasePageReq {
    /**
     * 钱包用户ID
     */
    private String walletUserId;
    /**
     * 用户名称
     */
    private String userName;

    private Integer type;

    private Integer subtype;

    private String transferNum;

    /**
     * 币种ID
     */
    private String coinId;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 第三方用户ID（eg: IM）
     */
    private List<String> threeUserIdList;

    private Integer searchType;
}
