package com.lxtx.im.admin.feign.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ppai
 * Date: 2019/3/12 上午11:53
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayCoinRateResp {

    private String id;

    private String coinId;

    private String coinName;

    private String feeRate;
}
