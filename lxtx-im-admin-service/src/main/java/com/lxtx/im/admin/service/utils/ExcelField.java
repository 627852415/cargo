package com.lxtx.im.admin.service.utils;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcelField {
    /**
     * 文件列名
     */
    String name();
    /**
     * 排序
     */
    String orderBy();
}
