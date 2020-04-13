package com.lxtx.im.admin.service;

import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author PengPai
 * Date: Created in 17:03 2020/2/24
 */
public interface NoticeManagerService {

    /**
     * 功能描述: 国际简码简单信息<br>
     *
     * @Param: []
     * @Return: List<GlobalCodeSimpleInfo>
     * @Author: peng
     * @Date: 2020/2/28 1:11
     */
    List<GlobalCodeSimpleInfo> globalCodeList();

    /**
     * 功能描述: 查询指令分页列表<br>
     *
     * @Param: [req]
     * @Return: com.lxtx.im.admin.service.response.BasePageResp<com.lxtx.im.admin.service.response.NoticeCommandResp>
     * @Author: peng
     * @Date: 2020/2/24 17:07
     */
    BasePageResp<NoticeCommandResp> commandPage(NoticeCommandPageReq req);

    /**
     * 功能描述: 发送指令<br>
     *
     * @Param: [req]
     * @Return: void
     * @Author: peng
     * @Date: 2020/2/24 17:08
     */
    void commandPush(NoticeCommandPushReq req) throws Exception;

    /**
     * 功能描述: 查询广播分页列表<br>
     *
     * @Param: [req]
     * @Return: com.lxtx.im.admin.service.response.BasePageResp<com.lxtx.im.admin.service.response.NoticeBroadCastResp>
     * @Author: peng
     * @Date: 2020/2/25 13:54
     */
    BasePageResp<NoticeBroadCastResp> broadcastPage(NoticeBroadCastPageReq req);

    /**
     * 功能描述: 根据ID查询详情<br>
     *
     * @Param: [req]
     * @Return: com.lxtx.im.admin.service.response.NoticeBroadCastResp
     * @Author: peng
     * @Date: 2020/2/25 18:19
     */
    NoticeBroadCastResp broadcastDetail(String id);

    /**
     * 功能描述: 根据ID集合删除<br>
     *
     * @Param: [req]
     * @Return: void
     * @Author: peng
     * @Date: 2020/2/26 10:09
     */
    void broadcastDelete(NoticeBroadCastDeleteReq req);

    /**
     * 功能描述: 保存广播<br>
     *
     * @Param: [req]
     * @Return: void
     * @Author: peng
     * @Date: 2020/2/26 10:15
     */
    NoticeBroadCast broadcastSave(NoticeBroadCastSaveReq req);

    /**
     * 功能描述: 发送广播<br>
     *
     * @Param: [req]
     * @Return: void
     * @Author: peng
     * @Date: 2020/2/26 10:16
     */
    void broadcastPush(NoticeBroadCastPushReq req) throws Exception;

    /**
     * 功能描述: 新增广播并发送<br>
     *
     * @Param: []
     * @Return: void
     * @Author: peng
     * @Date: 2020/2/29 18:12
     */
    void broadcastAddOrEditAndPush(NoticeBroadCastAddOrEditAndPushReq req) throws Exception;

    /**
     * 功能描述: 撤销广播<br>
     *
     * @Param: [req]
     * @Return: void
     * @Author: peng
     * @Date: 2020/3/2 13:58
     */
    void broadcastRepealPush(NoticeBroadCastPushReq req) throws Exception;

    /**
     * 功能描述: 公告发布定时任务<br>
     *
     * @Param: []
     * @Return: void
     * @Author: peng
     * @Date: 2020/3/4 16:15
     */
    void broadcastPushScheduler();

    /**
     * 功能描述: 上传公告文件。图片<br>
     *
     * @Param: s
     * @Return: java.lang.String
     * @Author: peng
     * @Date: 2020/3/12 14:11
     */
    String uploadBroadcast(MultipartFile file);
}
