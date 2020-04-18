package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssetStatisticsListExportReq extends BasePageReq {
    private Long recordsDate;
    /**
     * 币种ID
     */
    private String coinId;

}