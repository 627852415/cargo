package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TransferFriendsFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignTransferFriendsReq;
import com.lxtx.im.admin.feign.request.FeignTransferUserReq;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 
 * @Description 好友转账远程接口
 * @author qing 
 * @date: 2019年11月21日 下午6:16:19
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TransferFriendsFeignFallbackFactory.class)
public interface TransferFriendsFeign {
	
	/**
     * 
     * @Description 好友转账列表
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/friends/listPage")
    BaseResult listPage(@RequestBody FeignTransferFriendsReq req);
    
    
    /**
     * 
     * @Description 好友转账列表数
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/friends/listCount")
    BaseResult listCount(@RequestBody FeignTransferFriendsReq req);

    /**
     * 
     * @Description 好友转账列表
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/friends/list")
    BaseResult list(@RequestBody FeignTransferFriendsReq req);
    
    /**
     * 
     * @Description 好友转账列表昵称
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/userName/list")
    BaseResult listTransferUserNames(@RequestBody FeignTransferUserReq req);

}
