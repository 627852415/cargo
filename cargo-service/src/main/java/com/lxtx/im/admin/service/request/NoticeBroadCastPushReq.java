package com.lxtx.im.admin.service.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author PengPai
 * Date: Created in 15:11 2020/2/24
 */
@Data
@Builder
public class NoticeBroadCastPushReq {
    @NotBlank(message = "广播ID不能为空")
    private String id;
}
