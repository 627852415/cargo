package com.lxtx.im.admin.service.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Ppai
 * Date: 2019/3/12 上午11:53
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayCoinRateListReq {

    private String userId;

    private String cycleId;

    private Integer cycle;

    private List<PayCoinRateReq> coinList;
}
