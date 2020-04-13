package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 广播/推送消息请求参数
 *
 * @author CaiRH
 */
@Setter
@Getter
public class FeignBroadcastPushReq {
    /**
     * 推送用户类型
     */
    private Integer type;
    /**
     * 广播内容
     */
    private String content;

}
