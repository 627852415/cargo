package com.lxtx.im.admin.service.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author PengPai
 * Date: Created in 17:05 2020/2/24
 */
@Data
@Builder
public class NoticeCommandResp {

    private String id;

    private String instructId;

    private String name;

    private String operator;
}
