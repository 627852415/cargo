package com.lxtx.im.admin.web.config;



import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义填充公共字段
 * @author liboyan
 * @Date 2018-08-03 10:12
 * @Description
 */

public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }

        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }

        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (delFlag == null) {
            setFieldValByName("delFlag", false, metaObject);
        }

/*        Object createBy = getFieldValByName("createBy", metaObject);
        if (createBy == null && SecurityUtils.get() != null && !CheckUtils.isEmpty(SecurityUtils.getAccountId())) {
            setFieldValByName("createBy", SecurityUtils.getAccountId(),metaObject);
        }

        Object updateBy = getFieldValByName("updateBy", metaObject);
        if (updateBy == null &&  SecurityUtils.get() != null &&  !CheckUtils.isEmpty(SecurityUtils.getAccountId())) {
            setFieldValByName("updateBy", SecurityUtils.getAccountId(), metaObject);
        }*/


    }

    @Override
    public void updateFill(MetaObject metaObject) {

        setFieldValByName("updateTime", new Date(), metaObject);

/*        if (SecurityUtils.get() != null &&!CheckUtils.isEmpty(SecurityUtils.getAccountId())) {
            setFieldValByName("updateBy", SecurityUtils.getAccountId(), metaObject);
        }*/
    }
}