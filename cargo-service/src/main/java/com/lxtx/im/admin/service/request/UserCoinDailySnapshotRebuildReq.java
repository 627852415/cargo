package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @since 2019-07-09
 */
@Data
public class UserCoinDailySnapshotRebuildReq {

    @NotBlank(message = "快照时间不能为空")
    private String snapshotDay;
}
