package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/19 15:19
 */
@Getter
@Setter
public class FeignAlertAssetsReq extends BasePageReq {

    private Integer type;

    private Date createTime;

    private Date updateTime;
}
