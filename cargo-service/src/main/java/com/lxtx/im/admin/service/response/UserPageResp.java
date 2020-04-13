package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 钱包用户分页列表
 *
 * @Author liyunhua
 * @Date 2018-08-31 0031
 */
@Getter
@Setter
public class UserPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<WalletUserResp> records;

}
