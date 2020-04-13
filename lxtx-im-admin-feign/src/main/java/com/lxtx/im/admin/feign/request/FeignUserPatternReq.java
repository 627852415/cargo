package com.lxtx.im.admin.feign.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @since 2019-05-09
 */
@Setter
@Getter
@AllArgsConstructor
public class FeignUserPatternReq implements Serializable {

    private String account;
}
