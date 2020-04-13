package com.lxtx.im.admin.dao;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Czh
 * Date: 2018/8/17 下午12:08
 */
public interface BaseDao<T> extends IService<T> {

    /**
     * 根据实体类查询实体类
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 根据实体类查询集合
     * @param entity
     * @return
     */
    List<T> selectList(T entity);

    /**
     * 根据实体类delete
     * @param entity
     * @return
     */
    boolean delete(T entity);
}
