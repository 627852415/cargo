package com.lxtx.im.admin.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author PengPai
 * Date: Created in 18:20 2020/2/21
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBroadCast {

    private String id;

    //广播标题
    private String title;

    //广播内容
    private String content;

    private String html;

    //支持的国家和地区简码列表【如：["AF","CN"]】，所有国家用["ALL"]表示
    private String supportCountry;

    //发布状态【0：未发布，1：已发布，2：已撤销】
    private Integer status;

    //发布者ID
    private String publisherUserId;

    //发布策略【0：立即发布，1：定时发布】
    private Integer type;

    //发布时间
    private String publishTime;

    //撤销者ID
    private String repealerUserId;

    //撤销时间
    private String repealTime;
}
