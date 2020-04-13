package com.lxtx.im.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.dao.TimerTaskDao;
import com.lxtx.im.admin.dao.model.AppTimerTask;
import com.lxtx.im.admin.feign.feign.QuartzFeign;
import com.lxtx.im.admin.feign.request.FeignQuartzReq;
import com.lxtx.im.admin.service.QuartzService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.QuartzInfoResp;
import com.lxtx.im.admin.service.response.QuartzListPageResp;
import com.lxtx.im.admin.service.response.QuartzResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 任务实现类
 * @author: CXM
 * @create: 2018-08-31 09:58
 **/
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {
	@Autowired
	private QuartzFeign quartzFeign;
	@Autowired
	private TimerTaskDao timerTaskDao;

	@Override
	public QuartzListPageResp listPage(QuartzListPageReq req) {
		EntityWrapper<AppTimerTask> ew = new EntityWrapper<>();
		if (StringUtils.isNotBlank(req.getTaskDescribe())) {
			ew.like("task_describe", req.getTaskDescribe());
		}
		ew.orderBy("create_time", false);
		Page page = timerTaskDao.selectPage(req.getPage(), ew);

		List<AppTimerTask> records = page.getRecords();
		QuartzListPageResp resp = new QuartzListPageResp();
		if (CollectionUtils.isEmpty(records)) {
			return resp;
		}
		BeanUtils.copyProperties(page, resp);

		List<QuartzResp> quartzRespList = new ArrayList<>();
		for (AppTimerTask timerTask : records) {
			if (timerTask == null) {
				continue;
			}
			QuartzResp quartzResp = new QuartzResp();
			BeanUtils.copyProperties(timerTask, quartzResp);
			quartzRespList.add(quartzResp);
		}
		resp.setRecords(quartzRespList);
		return resp;
	}

	@Override
	public BaseResult delete(QuartzDeleteReq req) {
		// 删除任务
		FeignQuartzReq quartzReq = new FeignQuartzReq();
		List<String> list = new ArrayList<>();
		list.add(req.getTaskId());
		quartzReq.setJobIds(list);
		BaseResult result = quartzFeign.deleteJob(quartzReq);

		if (result.isSuccess()) {
			AppTimerTask task = getTaskById(req.getTaskId());
			timerTaskDao.delete(task);
		} else {
			BaseResult.error(null, result.getMsg());
		}

		return BaseResult.success();
	}

	@Override
	public QuartzInfoResp info(QuartzInfoReq req) {
		AppTimerTask timerTask = getTaskById(req.getTaskId());
		QuartzInfoResp resp = new QuartzInfoResp();
		BeanUtils.copyProperties(timerTask, resp);
		return resp;
	}

	@Override
	public BaseResult saveOrUpdate(QuartzSaveReq req) {
		if (StringUtils.isNotBlank(req.getTaskId())) {
			AppTimerTask timerTask = getTaskById(req.getTaskId());
			timerTask.setTaskName(req.getTaskName());
			timerTask.setTaskDescribe(req.getTaskDescribe());
			timerTask.setCronExpression(req.getCronExpression());
			timerTask.setUpdateBy(Constants.SYSTEM);
			boolean updateById = timerTaskDao.updateById(timerTask);
			if (updateById) {
				List<String> jobIds = new ArrayList<>();
				jobIds.add(req.getTaskId());
				FeignQuartzReq quartzReq = new FeignQuartzReq();
				quartzReq.setJobIds(jobIds);
				BaseResult jobInfo = quartzFeign.getJobInfo(quartzReq);
				if (jobInfo.getData() != null && jobInfo.getData().toString().equalsIgnoreCase("NORMAL")) {
					BaseResult result = quartzFeign.modifyJob(quartzReq);
					return result;
				}else {
					return BaseResult.error(null, "只能修改运行中的任务");
				}
			}
		} else {
			AppTimerTask timerTask = new AppTimerTask();
			timerTask.setTaskName(req.getTaskName());
			timerTask.setTaskDescribe(req.getTaskDescribe());
			timerTask.setCronExpression(req.getCronExpression());
			timerTask.setCreateBy(Constants.SYSTEM);
			timerTask.setUpdateBy(Constants.SYSTEM);
			timerTask.setStatus(false);
			timerTaskDao.insert(timerTask);
		}
		return BaseResult.success();
	}

	@Override
	public BaseResult run(QuartzRunReq req) {

		List<String> jobIds = new ArrayList<>();
		jobIds.add(req.getTaskId());
		FeignQuartzReq quartzReq = new FeignQuartzReq();
		quartzReq.setJobIds(jobIds);
		BaseResult jobInfo = quartzFeign.getJobInfo(quartzReq);
		if (jobInfo.getData() != null && jobInfo.getData().toString().equalsIgnoreCase("NONE")) {
			BaseResult startOneJob = quartzFeign.startOneJob(quartzReq);
			if (startOneJob.isSuccess()) {
				AppTimerTask timerTask = getTaskById(req.getTaskId());
				timerTask.setStatus(true);
				timerTask.setUpdateBy(Constants.SYSTEM);
				timerTaskDao.updateById(timerTask);
				return BaseResult.success();
			} else {
				return BaseResult.error(null, "运行失败");
			}
		} else {
			BaseResult startOneJob = quartzFeign.resumeJob(quartzReq);
			if (startOneJob.isSuccess()) {
				AppTimerTask timerTask = getTaskById(req.getTaskId());
				timerTask.setStatus(true);
				timerTask.setUpdateBy(Constants.SYSTEM);
				timerTaskDao.updateById(timerTask);
				return BaseResult.success();
			} else {
				return BaseResult.error(null, "运行失败");
			}
		}
	}

	@Override
	public BaseResult stop(QuartzStopReq req) {

		List<String> jobIds = new ArrayList<>();
		jobIds.add(req.getTaskId());
		FeignQuartzReq quartzReq = new FeignQuartzReq();
		quartzReq.setJobIds(jobIds);
		BaseResult pauseJob = quartzFeign.pauseJob(quartzReq);
		if (pauseJob.isSuccess()) {
			AppTimerTask timerTask = getTaskById(req.getTaskId());
			timerTask.setStatus(false);
			timerTask.setUpdateBy(Constants.SYSTEM);
			timerTaskDao.updateById(timerTask);
			return BaseResult.success();
		} else {
			return BaseResult.error(null, "停止失败");
		}

	}

	/**
	 * 根据id获取任务
	 *
	 * @param taskId
	 * @return
	 */
	private AppTimerTask getTaskById(String taskId) {
		AppTimerTask timerTask = timerTaskDao.selectById(taskId);
		if (timerTask == null) {
			throw LxtxBizException.newException("该任务不存在！");
		}
		return timerTask;
	}

}
