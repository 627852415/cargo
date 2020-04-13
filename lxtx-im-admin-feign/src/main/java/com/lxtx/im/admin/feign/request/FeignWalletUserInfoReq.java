package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/11 11:23
 */
@Setter
@Getter
public class FeignWalletUserInfoReq {

    private String userId;

    private List<String> coinIdList;
}
