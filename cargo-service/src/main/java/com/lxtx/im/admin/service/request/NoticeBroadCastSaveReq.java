package com.lxtx.im.admin.service.request;

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
 * Date: Created in 15:08 2020/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeBroadCastSaveReq {

    //ID
    private String id;

    //标题
    @NotBlank(message = "标题不能为空")
    private String title;

    //内容
    @NotBlank(message = "内容不能为空")
    private String content;

    @NotBlank(message = "内容HTML不能为空")
    private String html;

    //国家简码列表
    private List<String> countryCodes;

    //发布状态【0：未发布，1：已发布，2：已撤销】
    @NotNull(message = "发布状态不能为空")
    private Integer status;

    //发布策略【0：立即发布，1：定时发布】
    @NotNull(message = "发布策略不能为空")
    private Integer type;

    //发布者ID
//    @NotBlank(message = "发布者ID不能为空")
    private String publisherUserId;

    //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;

    //撤销者ID
    private String repealerUserId;

    //撤销时间
    private Date repealTime;
}
