package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.request.RequestHeaderInfo;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-05-10 16:16
 * @Description
 */
@Data
public class BroadcastSwitchMessageResp {


    private Integer type;

    private List<RequestHeaderInfo> channelVersionArr;

    /**
     * 特殊国家列表
     */
    private String[] countryCodeArr;

    public BroadcastSwitchMessageResp(Integer type, List<RequestHeaderInfo> channelVersionArr) {
        this.type = type;
        this.channelVersionArr = channelVersionArr;
    }
}
