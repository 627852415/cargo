package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NoticeWhiteListResp {

    private String id;

    private String userId;

    private String telephone;

    private Integer type;

    private String name;

    private String countryCode;

    private Date createTime;
    
    private Date updateTime;
}
