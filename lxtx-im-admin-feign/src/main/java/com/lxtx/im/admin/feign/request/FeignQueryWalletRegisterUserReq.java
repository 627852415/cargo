package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 查询已经注册存在的对应钱包用户ID参数类
 *
 * @author CaiRH
 */
@Getter
@Setter
public class FeignQueryWalletRegisterUserReq {

    private List<String> accounts;

}
