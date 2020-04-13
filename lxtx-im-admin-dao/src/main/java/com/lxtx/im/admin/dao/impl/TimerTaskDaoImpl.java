package com.lxtx.im.admin.dao.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lxtx.im.admin.dao.TimerTaskDao;
import com.lxtx.im.admin.dao.model.AppTimerTask;

@Service
public class TimerTaskDaoImpl extends BaseDaoImpl<BaseMapper<AppTimerTask>, AppTimerTask> implements TimerTaskDao{

}
