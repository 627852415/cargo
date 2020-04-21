package com.lxtx.im.admin.service.cargo.req;

import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Lin hj
 * @title: PaperListPage
 * @projectName work
 * @description: TODO
 * @date 2020/4/1922:08
 */
@Setter
@Getter
public class PaperListPage {

    private String  typeId;

    /**
     * 页码（默认1页）
     */
    private Integer current = Constants.PAGE_CURRENT;
    /**
     * 长度（默认长度10）
     */
    private Integer size = Constants.PAGE_SIZE;

    @SuppressWarnings("Duplicates")
    public Page getPage() {
        Page page = new Page(current, size);

        if (StringUtils.isNotBlank(field)) {
            page.setOrderByField(field);
        }

        if (StringUtils.isNotBlank(order)) {
            if ("asc".equalsIgnoreCase(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
        }

        return page;
    }

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序方式  asc/desc
     */
    private String order;

}
