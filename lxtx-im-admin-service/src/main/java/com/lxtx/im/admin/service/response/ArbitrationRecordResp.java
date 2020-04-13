package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* @description:  投诉记录列表返回
* @author:   CXM
* @create:   2019-04-26 10:31
*/
@Data
public class ArbitrationRecordResp {

    /**
     * 图片路径,多个英文逗号分隔
     */
    private List<String> images;

    /**
     * 处理说明
     */
    private String content;

    private Date createTime;
    /**
     * 操作人
     */
    private String createBy;

    /**
     * 操作来源（0：系统，1:IM）
     */
    private Integer sourceType;

    private String sourceTypeName;
}
