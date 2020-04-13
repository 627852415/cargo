package com.lxtx.im.admin.service.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ppai
 * Date: 2019/3/14 上午11:53
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayCoinAmountResp {

    private String coinId;

    private String coinName;

    private String amount;
}
