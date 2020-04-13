package com.lxtx.im.admin.dao.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lxtx.im.admin.dao.BaseDao;

import java.util.List;

/**
 * @author Czh
 * Date: 2018/8/17 下午12:07
 */
public class BaseDaoImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseDao<T> {

    @Override
    public T selectOne(T entity) {
        return baseMapper.selectOne(entity);
    }

    @Override
    public List<T> selectList(T entity) {
        EntityWrapper<T> wrapper= new EntityWrapper<>();
        wrapper.setEntity(entity);
        return selectList(wrapper);
    }

    @Override
    public boolean delete(T entity) {
        EntityWrapper<T> wrapper= new EntityWrapper<>();
        wrapper.setEntity(entity);
        return delete(wrapper);
    }
}