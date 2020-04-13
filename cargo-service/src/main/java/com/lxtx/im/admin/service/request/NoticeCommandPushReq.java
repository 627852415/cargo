package com.lxtx.im.admin.service.request;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author PengPai
 * Date: Created in 14:48 2020/2/24
 */
@Data
@Builder
public class NoticeCommandPushReq {

    /**
     * 指令实例ID
     */
    @NotBlank(message = "指令ID不能为空")
    private String instructId;
}
