package com.lxtx.im.admin.feign.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * 通过编码查询国际简码列表
 *
 * @author liyunhua
 * @since 2018-08-27
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeignGlobalCodeByCountryReq {
    /**
     * 国际简码集合
     */
    private List<String> countryCodes;
}
