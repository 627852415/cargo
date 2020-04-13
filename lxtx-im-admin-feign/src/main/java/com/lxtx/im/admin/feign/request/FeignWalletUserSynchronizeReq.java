package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
* @description:  钱包用户同步数据
* @author:   xff
* @create:   2019-12-5
*/
@Data
public class FeignWalletUserSynchronizeReq {

    private String jsonUserList;
 
}
