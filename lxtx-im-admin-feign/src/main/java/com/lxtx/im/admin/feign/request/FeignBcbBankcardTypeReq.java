package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/2 11:04
 */
@Getter
@Setter
public class FeignBcbBankcardTypeReq extends BasePageReq {

    private String coinId;

    private Integer cardType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
