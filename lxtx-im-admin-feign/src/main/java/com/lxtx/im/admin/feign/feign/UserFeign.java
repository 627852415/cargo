package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.UserFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tangdy
 * @Date 2018-09-04
 * @Description
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeign {


    @PostMapping("/user/init/user/uid")
    BaseResult initUserUid() ;

    /**
     * 查询柬埔寨account
     * @return
     */
    @PostMapping("/user/kh/user/list")
    BaseResult selectKhUser() ;

    /**
     * 查询用户列表
     *
     * @param userListReq
     * @return
     */
    @PostMapping(value = "/user/listPage")
    BaseResult listPage(FeignUserListReq userListReq);

    /**
     * 停用或启用账号
     *
     * @param userStateOperateReq
     * @return
     */
    @PostMapping(value = "/user/operateState")
    BaseResult operateState(FeignUserStateOperateReq userStateOperateReq);

    /**
     * 重置密码
     *
     * @param userStateOperateReq
     * @return
     */
    @PostMapping(value = "/user/resetPsd")
    BaseResult resetPsd(FeignUserResetPsdReq userStateOperateReq);

    /**
     * 修改用户信息
     *
     * @param userModifyReq
     * @return
     */
    @PostMapping(value = "/user/modify")
    BaseResult modify(FeignUserModifyReq userModifyReq);

    /**
     * 根据用户id集合批量查询用户信息
     *
     * @param queryUserListReq
     * @return
     */
    @PostMapping(value = "user/query/list")
    BaseResult queryList(@RequestBody FeignQueryUserListReq queryUserListReq);


    /**
     * 根据查询条件批量查询用户信息
     *
     * @param userReq
     * @return
     */
    @PostMapping(value = "user/list")
    BaseResult list(@RequestBody FeignMemberListReq userReq);


    /**
     * 通过用户名称查找用户ID
     *
     * @param queryUsernameReq
     * @return
     */
    @PostMapping(value = "user/query/username")
    BaseResult queryByUsername(@RequestBody FeignQueryUsernameReq queryUsernameReq);

    /**
     * 获取最新的各个国家手机区号列表
     *
     * @return
     */
    @PostMapping(value = "user/global/code")
    BaseResult getGlobalCodeList(@RequestBody FeignGetGlobalCodeListReq req);


    /**
     * 查询有效的用户列表
     *
     * @return
     */
    @PostMapping(value = "user/query/active")
    BaseResult queryActive();

    /**
     * 查询有效的用户列表
     *
     * @return
     */
    @PostMapping(value = "user/query/batchList")
    BaseResult selectBatchIds(FeignQueryUserListByIdReq req);

    /**
     * 查询有效的用户列表
     *
     * @return
     */
    @PostMapping(value = "user/synchronize/yunxin")
    BaseResult synchronizeYunxinUser(FeignYunXinCreateReq req);

    /**
     * 用户详情
     *
     * @param req
     * @return
     */
    @PostMapping(value = "user/detail")
    BaseResult detail(FeignUserDetailReq req);

    @PostMapping(value = "user/account/list")
    BaseResult getUserAccountByUserInfo(FeignUserInfoReq feignReq);

    /**
     * 模糊查询IM用户
     *
     * @param req
     * @return
     */
    @PostMapping(value = "user/account/like")
    BaseResult queryListLikeAccount(FeignQueryUserLikeAccountReq req);

    /**
     * 模糊查询IM用户
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/user/get/user/message")
    BaseResult get(FeignUserReq req);

    /**
     * 查询出所有用户数据，用于同步至钱包
     *
     * @param
     * @return
     */
    @PostMapping(value = "user/query/all/list")
    BaseResult queryListToWalletUser();

    /**
     * 功能描述: 初始化agora uid
     *
     * @return
     * @author Czh
     * @date 2019/12/5 4:44 下午
     */
    @PostMapping(value = "/user/init/agora")
    BaseResult initAgoraUid();

    @PostMapping(value = "/user/kickOffline")
    BaseResult kickOffline(FeignKickUserOfflineReq req);
}
