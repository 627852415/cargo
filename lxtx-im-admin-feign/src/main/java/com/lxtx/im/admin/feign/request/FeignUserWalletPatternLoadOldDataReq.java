package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 钱包模式 - 旧数据迁移
 *
 * @since 2019-05-09
 */
@Getter
@Setter
public class FeignUserWalletPatternLoadOldDataReq {

    private List<UserWalletPatternLoadOldData> list;

    @Getter
    @Setter
    public static class UserWalletPatternLoadOldData {
        private String account;
        private List<Integer> patterns;
    }

}