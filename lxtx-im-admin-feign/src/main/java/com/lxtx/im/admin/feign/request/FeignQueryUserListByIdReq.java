package com.lxtx.im.admin.feign.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeignQueryUserListByIdReq {

    /**
     * 用户id集合
     */
    private List<String> ids;

}
