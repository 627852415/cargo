package com.lxtx.im.admin.service.transaction;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

import com.lxtx.im.admin.service.request.TransferFriendsReq;

/**
 * 
 * @Description 异步分页业务接口
 * @author qing 
 * @date: 2019年11月20日 下午2:58:54
 */
public interface AsyncTransferFriendsService {

	/**
	 * 
	 * @Description 异步获取好友转账列表数据
	 * @param req
	 * @param segmentedFile
	 * @param cdl
	 * @param session
	 */
	void asyncListPage(TransferFriendsReq req, File segmentedFile, CountDownLatch cdl, HttpSession session);
	
}
