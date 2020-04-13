package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 群游戏列表请求参数
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Setter
@Getter
public class FeignGroupGameListPageReq extends BasePageReq {

    /**
     * 群id集合
     */
    private List<String> groupIds;


}
