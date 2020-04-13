package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.*;

/**
 * @author PengPai
 * Date: Created in 14:43 2020/2/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class NoticeCommandPageReq extends BasePageReq {
    //指令名称
    private String name;
    //指令操作
    private String operator;
}
