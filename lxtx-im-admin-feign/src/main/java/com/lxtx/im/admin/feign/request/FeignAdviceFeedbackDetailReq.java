package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/5 13:50
 */
@Getter
@Setter
public class FeignAdviceFeedbackDetailReq implements Serializable {
    private final static long serialVersionUID = 1L;

    private String id;
}
