package com.lxtx.im.admin.feign.request;

import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.constants.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * @author CaiRH
 * @since 2019-05-27
 */
@Setter
@Getter
public class FeignBasePageReq {
    /**
     * 页码（默认1页）
     */
    private Integer current = Constants.PAGE_CURRENT;
    /**
     * 长度（默认长度10）
     */
    private Integer size = Constants.PAGE_SIZE;

    public Page getPage(){
        return new Page(current, size);
    }

    /**
     * 排序字段
     * */
    private String field;

    /**
     * 排序方式  asc/desc
     * */
    private String order;
}