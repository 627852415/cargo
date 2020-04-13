package com.lxtx.im.admin.feign.request;

import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.constants.Constants;
import lombok.Getter;
import lombok.Setter;


/**
 * 查询异常记录参数类
 *
 * @author tangdy
 * @since 2018-08-27
 */
@Getter
@Setter
public class FeignExceptionRecordReq{
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private Integer type;

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
}
