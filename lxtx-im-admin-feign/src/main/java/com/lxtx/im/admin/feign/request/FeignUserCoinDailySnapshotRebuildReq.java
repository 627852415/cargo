package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * @since 2019-07-09
 */
@Data
public class FeignUserCoinDailySnapshotRebuildReq {

    private String snapshotDay;
}
