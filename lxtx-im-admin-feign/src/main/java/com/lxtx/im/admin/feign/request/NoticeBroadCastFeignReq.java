package com.lxtx.im.admin.feign.request;

import lombok.*;

import java.util.Date;
import java.util.Set;

/**
 * @author PengPai
 * Date: Created in 14:21 2020/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class NoticeBroadCastFeignReq extends BasePageReq {

    //主键集合
    private Set<String> ids;

    //广播标题
    private String title;

    //广播内容
    private String content;

    //内容html
    private String html;

    //支持的国家和地区简码列表【如：["AF","CN"]】，所有国家用["ALL"]表示
    private String supportCountry;

    //发布状态【0：未发布，1：已发布，2：已撤销】
    private Integer publishStatus;

    //发布者ID
    private String publisherUserId;

    //发布策略【0：立即发布，1：定时发布】
    private Integer type;

    //发布时间
    private Date publishTime;

    //搜索发布时间开始
    private Date searchPublishTimeBegin;

    //搜索发布时间结束
    private Date searchPublishTimeEnd;

    //撤销者ID
    private String repealerUserId;

    //撤销时间
    private Date repealTime;

    //搜索撤销时间开始
    private Date searchRepealTimeBegin;

    //搜索撤销时间结束
    private Date searchRepealTimeEnd;
}
