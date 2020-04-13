package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author PengPai
 * Date: Created in 13:53 2020/2/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeBroadCastResp {

    //广播ID
    private String id;

    //广播标题
    private String title;

    //广播内容
    private String content;

    private String html;

    //国家名称列表
    private List<String> countryNames;

    //国家简码列表
    private List<String> countryCodes;

    //发布者ID
    private String publisherUserId;

    //发布者名字
    private String publisherName;

    //发布时间
    private String publishTime;

    //发布状态【0：未发布，1：已发布，2：已撤销】
    private Integer status;

    //发布状态名【0：未发布，1：已发布，2：已撤销】
    private String statusName;

    private Integer type;

    //撤销者ID
    private String repealerUserId;

    //撤销者名称
    private String repealerName;
    //撤销时间
    private String repealTime;
}
