package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 广播/推送消息请求参数
 *
 * @author CaiRH
 */
@Setter
@Getter
public class BroadcastPushReq {
    @NotBlank(message = "sendAccount 参数不能为空")
    private String sendAccount;

    @NotBlank(message = "sendName 参数不能为空")
    private String sendName;

    /**
     * 推送用户类型
     */
    @NotNull(message = "请选择广播推送用户类型")
    private Integer type;
    /**
     * 广播内容
     */
    @NotBlank(message = "内容不能为空")
    @Length(max = 2000, message = "广播内容太长了")
    private String content;

    @NotBlank(message = "html 内容不能为空")
    private String html ;

    @NotBlank(message = "title 参数不能为空")
    private String title ;

}
