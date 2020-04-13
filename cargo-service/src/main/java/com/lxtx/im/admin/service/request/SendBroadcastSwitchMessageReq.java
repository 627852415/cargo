package com.lxtx.im.admin.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-05-10 14:50
 * @Description
 */
@Data
public class SendBroadcastSwitchMessageReq {

    @NotNull(message = "type 参数不能为空")
    private Integer type;
}
