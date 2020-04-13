package com.lxtx.im.admin.service.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author PengPai
 * Date: Created in 18:13 2020/2/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeBroadCastAddOrEditAndPushReq {

    private String id;
    //消息标题
    @NotBlank(message = "标题不能为空")
    private String title;

    //消息内容
    @NotBlank(message = "内容不能为空")
    private String content;

    //消息html
    private String html;

    //支持的国家简码
    @NotNull(message = "支持的国家不能为空")
    private List<String> countryCodes;

    //发布类型
    @NotNull(message = "发布类型不能为空")
    private Integer type;

    //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd", timezone = "GMT+8")
    private String publishTime;
}
