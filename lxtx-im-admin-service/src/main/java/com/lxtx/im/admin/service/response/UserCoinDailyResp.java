package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author Husky
 * @create 2019-06-01
 */
@Getter
@Setter
public class UserCoinDailyResp {
    List<UserCoinDailyDetalResp> list;
}
