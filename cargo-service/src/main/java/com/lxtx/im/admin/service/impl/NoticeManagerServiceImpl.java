package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.MessageUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.message.FeignMessageOption;
import com.lxtx.framework.common.utils.message.FeignMessageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.feign.NoticeBroadCastFeign;
import com.lxtx.im.admin.feign.feign.NoticeCommandFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.PropertiesContants;
import com.lxtx.im.admin.service.NoticeManagerService;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.enums.EnumNoticeBroadCastStatus;
import com.lxtx.im.admin.service.enums.EnumNoticeBroadCastType;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.notice.INoticePush;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PengPai
 * Date: Created in 17:09 2020/2/24
 */
@Service
@Slf4j
public class NoticeManagerServiceImpl implements NoticeManagerService {

    @Autowired
    private SysUserService sysUserService;
    @Resource
    private NoticeCommandFeign commandFeign;
    @Resource
    private NoticeBroadCastFeign broadCastFeign;
    @Autowired
    private ApplicationContext applicationContext;
    @Resource
    private GlobalCodeFeign globalCodeFeign;
    @Resource
    private QiNiuUtil qiNiuUtil;


    private String detailUrl = PropertiesUtil.getString(PropertiesContants.BROADCAST_DETAIL_URL);
    private static final String SPACE = " ";
    private static final String DATE_BEGIN = SPACE + "00:00:00";
    private static final String DATE_END = SPACE + "23:59:59";

    @Override
    public List<GlobalCodeSimpleInfo> globalCodeList() {
        FeignGlobalCodeListReq req = new FeignGlobalCodeListReq();
        req.setCurrent(1);
        req.setSize(1000);
        BaseResult result = globalCodeFeign.listPage(req);
        if (!result.isSuccessAndDataNotNull()) {
            return null;
        }
        String s = JSONObject.toJSONString(result.getData());
        BasePageResp<GlobalCodeResp> pageResp = JSONObject.parseObject(s, new TypeReference<BasePageResp<GlobalCodeResp>>() {
        });
        List<GlobalCodeResp> records = pageResp.getRecords();
        return Optional.ofNullable(records).orElse(new ArrayList<>())
                .parallelStream()
                .map(resp -> {
                    return GlobalCodeSimpleInfo.builder()
                            .countryCode(resp.getCountryCode())
                            .countryName(resp.getCnName())
                            .phoneCode(resp.getPhoneCode())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public BasePageResp<NoticeCommandResp> commandPage(NoticeCommandPageReq req) {
        NoticeCommandFeignReq feignReq = NoticeCommandFeignReq.builder()
                .name(req.getName())
                .description(req.getOperator())
                .build();
        feignReq.setSize(req.getSize());
        feignReq.setCurrent(req.getCurrent());
        BaseResult result = commandFeign.page(feignReq);

        BasePageResp<NoticeCommandResp> resp = new BasePageResp<>();
        if (!result.isSuccessAndDataNotNull()) {
            return resp;
        }

        BasePageResp<NoticeCommand> basePageResp = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), new TypeReference<BasePageResp<NoticeCommand>>() {
        });
        List<NoticeCommand> noticeCommands = basePageResp.getRecords();

        List<NoticeCommandResp> commandResps = noticeCommands.parallelStream()
                .map(noticeCommand -> {
                    return NoticeCommandResp.builder()
                            .id(noticeCommand.getId())
                            .instructId(noticeCommand.getInstructId())
                            .name(noticeCommand.getName())
                            .operator(noticeCommand.getDescription())
                            .build();
                }).collect(Collectors.toList());

        BeanUtils.copyProperties(basePageResp, resp);
        resp.setRecords(commandResps);

        return resp;
    }

    @Override
    public void commandPush(NoticeCommandPushReq req) throws Exception {
        String instructId = req.getInstructId();
        if (applicationContext.containsBean(instructId)) {
            INoticePush bean = applicationContext.getBean(instructId, INoticePush.class);
            com.lxtx.framework.common.utils.message.FeignSendMsgReq sendMsgReq = bean.getCommandReq();
            BaseResult result = MessageUtil.sendBroadcastMsg(sendMsgReq);
            if (!result.isSuccess()) {
                throw LxtxBizException.newException(result.getCode(), result.getMsg());
            }
        } else {
            throw LxtxBizException.newException(instructId + ":指令不存在");
        }
    }

    @Override
    public BasePageResp<NoticeBroadCastResp> broadcastPage(NoticeBroadCastPageReq req) {
        Set<String> ids = null;
        if (StringUtils.isNotBlank(req.getId())) {
            ids = new HashSet<>();
            ids.add(req.getId());
        }

        String countryCode = req.getCountryCode();
        if (StringUtils.isNotBlank(countryCode) && StringUtils.equalsIgnoreCase(countryCode, "ALL")) {
            countryCode = null;
        }
        Date searchBeginTime = req.getSearchBeginTime();
        Date searchEndTime = req.getSearchEndTime();
        if (searchBeginTime != null && searchEndTime != null) {
            String beginDateFormat = DateUtils.getDateFormat(searchBeginTime, DateUtils.DATE_FORMAT_YYYY_MM_DD);
            searchBeginTime = DateUtils.formatDate(beginDateFormat + DATE_BEGIN, DateUtils.DATE_FORMAT_DEFAULT);
            String endDateFormat = DateUtils.getDateFormat(searchEndTime, DateUtils.DATE_FORMAT_YYYY_MM_DD);
            searchEndTime = DateUtils.formatDate(endDateFormat + DATE_END, DateUtils.DATE_FORMAT_DEFAULT);
        }
        NoticeBroadCastFeignReq feignReq = NoticeBroadCastFeignReq.builder()
                .ids(ids)
                .title(req.getTitle())
                .content(req.getContent())
                .supportCountry(countryCode)
                .searchPublishTimeBegin(searchBeginTime)
                .searchPublishTimeEnd(searchEndTime)
                .publishStatus(req.getStatus())
                .build();
        feignReq.setSize(req.getSize());
        feignReq.setCurrent(req.getCurrent());
        feignReq.setField("publish_time");
        feignReq.setOrder("desc");
        BaseResult page = broadCastFeign.page(feignReq);

        BasePageResp<NoticeBroadCastResp> resps = new BasePageResp<>();
        if (!page.isSuccessAndDataNotNull()) {
            return resps;
        }

        String s = JSONObject.toJSONString(page.getData());
        BasePageResp<NoticeBroadCast> pageResp = JSONObject.parseObject(s, new TypeReference<BasePageResp<NoticeBroadCast>>() {
        });
        List<NoticeBroadCast> records = pageResp.getRecords();

        if (CollectionUtils.isEmpty(records)) {
            return resps;
        }

        List<NoticeBroadCastResp> resp = records.parallelStream()
                .map(broadcast -> {
                    String sc = broadcast.getSupportCountry();
                    //支持的国家简码列表
                    List<String> cts = strToCodeList.apply(sc);
                    //国家名称列表
                    List<String> supportCountryNames;
                    if (cts.contains("ALL")) {
                        supportCountryNames = Arrays.asList("全部国家");
                    } else {
                        supportCountryNames = codeListToNameList.apply(cts);
                    }
                    //发布者
                    String publisherUserId = broadcast.getPublisherUserId();
                    SysUser publishUser = sysUserService.selectById(publisherUserId);

                    String repealerUserId = broadcast.getRepealerUserId();
                    SysUser repealUser = sysUserService.selectById(repealerUserId);
                    //发布状态
                    Integer status = broadcast.getStatus();
                    return NoticeBroadCastResp.builder()
                            .id(broadcast.getId())
                            .title(broadcast.getTitle())
                            .content(broadcast.getContent())
                            .html(broadcast.getHtml())
                            .countryCodes(cts)
                            .countryNames(supportCountryNames)
                            .publisherName(publishUser != null ? publishUser.getUsername() : null)
                            .publisherUserId(publisherUserId)
                            .publishTime(broadcast.getPublishTime())
                            .status(status)
                            .statusName(status == null ? null : EnumNoticeBroadCastStatus.find(status).getDescription())
                            .type(broadcast.getType())
                            .repealerUserId(repealerUserId)
                            .repealerName(repealUser != null ? repealUser.getUsername() : null)
                            .repealTime(broadcast.getRepealTime())
                            .build();

                })
                .collect(Collectors.toList());

        BeanUtils.copyProperties(pageResp, resps);
        resps.setRecords(resp);
        return resps;
    }

    @Override
    public NoticeBroadCastResp broadcastDetail(String id) {

        NoticeBroadCastFeignReq feignReq = NoticeBroadCastFeignReq.builder()
                .ids(Stream.of(id).collect(Collectors.toSet()))
                .build();
        BaseResult result = broadCastFeign.list(feignReq);

        NoticeBroadCastResp resp = new NoticeBroadCastResp();
        if (!result.isSuccessAndDataNotNull()) {
            return resp;
        }

        NoticeBroadCast noticeBroadCast = baseListToObj.apply(result);

        resp = Optional.ofNullable(noticeBroadCast)
                .map(broadcast -> {
                    Integer status = broadcast.getStatus();

                    String publisherUserId = broadcast.getPublisherUserId();
                    SysUser publishUser = sysUserService.selectById(publisherUserId);
                    String repealerUserId = broadcast.getRepealerUserId();
                    SysUser repealUser = sysUserService.selectById(repealerUserId);

                    String sc = broadcast.getSupportCountry();
                    //支持的国家简码列表
                    List<String> cts = strToCodeList.apply(sc);
                    //国家名称列表
                    List<String> supportCountryNames = codeListToNameList.apply(cts);
                    return NoticeBroadCastResp.builder()
                            .statusName(status == null ? null : EnumNoticeBroadCastStatus.find(status).getDescription())
                            .status(status)
                            .publisherUserId(publisherUserId)
                            .publisherName(publishUser != null ? publishUser.getUsername() : null)
                            .countryNames(supportCountryNames)
                            .countryCodes(cts)
                            .type(broadcast.getType())
                            .content(broadcast.getContent())
                            .html(broadcast.getHtml())
                            .title(broadcast.getTitle())
                            .id(broadcast.getId())
                            .publishTime(broadcast.getPublishTime())
                            .repealerUserId(repealerUserId)
                            .repealerName(repealUser != null ? repealUser.getUsername() : null)
                            .repealTime(broadcast.getRepealTime())
                            .build();
                }).get();

        return resp;
    }

    @Override
    public void broadcastDelete(NoticeBroadCastDeleteReq req) {

        NoticeBroadCastFeignReq feignReq = NoticeBroadCastFeignReq.builder()
                .ids(req.getIds())
                .build();
        broadCastFeign.deleteByIds(feignReq);
    }

    @Override
    public NoticeBroadCast broadcastSave(NoticeBroadCastSaveReq req) {
        Set<String> ids = null;
        if (StringUtils.isNotBlank(req.getId())) {
            ids = new HashSet<>();
            ids.add(req.getId());
        } else {
            SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
            req.setPublisherUserId(sysUser.getUserId());
        }

        if (req.getType() != null && req.getType() == 0) {
            req.setPublishTime(new Date());
        }
        List<String> countryCodes = req.getCountryCodes();
        List<String> stringList = Optional.ofNullable(countryCodes).orElse(new ArrayList<>());
        String supportCountry = JSONObject.toJSONString(stringList);
        NoticeBroadCastFeignReq feignReq = NoticeBroadCastFeignReq.builder()
                .ids(ids)
                .publishStatus(req.getStatus())
                .supportCountry(supportCountry)
                .content(req.getContent())
                .html(req.getHtml())
                .title(req.getTitle())
                .publisherUserId(req.getPublisherUserId())
                .publishTime(req.getPublishTime())
                .repealerUserId(req.getRepealerUserId())
                .repealTime(req.getRepealTime())
                .type(req.getType())
                .build();
        BaseResult result = broadCastFeign.insertOrUpdate(feignReq);
        if (!result.isSuccessAndDataNotNull()) {
            return null;
        }
        NoticeBroadCast broadcast = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), new TypeReference<NoticeBroadCast>() {
        });
        return broadcast;
    }

    @Override
    public void broadcastPush(NoticeBroadCastPushReq req) throws Exception {

        NoticeBroadCastFeignReq feignReq = NoticeBroadCastFeignReq.builder()
                .ids(Stream.of(req.getId()).collect(Collectors.toSet()))
                .build();
        BaseResult list = broadCastFeign.list(feignReq);

        if (!list.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("没找到广播信息");
        }
        NoticeBroadCast broadCast = baseListToObj.apply(list);

        String sc = broadCast.getSupportCountry();
        List<String> codeList = strToCodeList.apply(sc);

        Map<String, Object> content = new HashMap<>();
        content.put("key", broadCast.getContent());
        content.put("html", broadCast.getHtml());
        //新增公告ID
        content.put("broadcaseId", broadCast.getId());
        content.put("detailUrl", detailUrl);
        content.put("supportCountry", codeList);

//        String sc = broadCast.getSupportCountry();
//        List<String> codeList = strToCodeList.apply(sc);
        Map<String, Object> extra = new HashMap<>(1);
        extra.put("senderName", Constants.SYSTEM);
//        extra.put("supportCountry", codeList);

        NoticeMessageReq messageReq = NoticeMessageReq.builder()
                .action(Constants.MessageAction.ACTION_1001)
                .title(broadCast.getTitle())
                .content(content)
                .extra(extra)
                .sender(Constants.SYSTEM)
                .receiver(Constants.SYSTEM)
                .build();
        //发送广播
        push(messageReq);

        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //更新发布状态
        NoticeBroadCastFeignReq updateStatus = NoticeBroadCastFeignReq.builder()
                .ids(Stream.of(broadCast.getId()).collect(Collectors.toSet()))
                .publishStatus(EnumNoticeBroadCastStatus.PUBLISHED.getCode())
                .publisherUserId(sysUser.getUserId())
                .publishTime(new Date())
                .build();
        broadCastFeign.insertOrUpdate(updateStatus);
    }

    @Override
    public void broadcastAddOrEditAndPush(NoticeBroadCastAddOrEditAndPushReq req) throws Exception {
        Set<String> ids = null;
        Integer status = null;
        if (StringUtils.isNotBlank(req.getId())) {
            ids = new HashSet<>();
            ids.add(req.getId());
        } else {
            status = EnumNoticeBroadCastStatus.NO_PUBLISH.getCode();
        }

        Date publishTime;
        if (EnumNoticeBroadCastType.IMMEDIATELY.getCode().equals(req.getType())) {
            publishTime = new Date();
        } else {
            if (StringUtils.isBlank(req.getPublishTime())) {
                throw LxtxBizException.newException("发布时间不能为空");
            }
            String pt = req.getPublishTime();
            publishTime = DateUtils.formatDate(pt, DateUtils.DATE_FORMAT_DEFAULT);
        }

        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        NoticeBroadCastFeignReq feignReq = NoticeBroadCastFeignReq.builder()
                .ids(ids)
                .title(req.getTitle())
                .content(req.getContent())
                .html(req.getHtml())
                .supportCountry(JSONObject.toJSONString(req.getCountryCodes()))
                .publishStatus(status)
                .publishTime(publishTime)
                .type(req.getType())
                .publisherUserId(sysUser.getUserId())
                .build();
        BaseResult result = broadCastFeign.insertOrUpdate(feignReq);
        if (!result.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("保存广播失败");
        }
        NoticeBroadCast broadcast = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), new TypeReference<NoticeBroadCast>() {
        });

        if (EnumNoticeBroadCastType.IMMEDIATELY.getCode().equals(broadcast.getType())) {
            Map<String, Object> content = new HashMap<>();
            content.put("key", broadcast.getContent());
            content.put("html", broadcast.getHtml());
            //新增公告ID
            content.put("broadcaseId", broadcast.getId());
            content.put("detailUrl", detailUrl);
            content.put("supportCountry", req.getCountryCodes());

            Map<String, Object> extra = new HashMap<>(1);
            extra.put("senderName", Constants.SYSTEM);
//            extra.put("supportCountry", req.getCountryCodes());
            NoticeMessageReq messageReq = NoticeMessageReq.builder()
                    .action(Constants.MessageAction.ACTION_1001)
                    .title(broadcast.getTitle())
                    .content(content)
                    .extra(extra)
                    .sender(Constants.SYSTEM)
                    .receiver(Constants.SYSTEM)
                    .build();
            //发送消息
            push(messageReq);

            //更新发布状态
            NoticeBroadCastFeignReq updateStatus = NoticeBroadCastFeignReq.builder()
                    .ids(Stream.of(broadcast.getId()).collect(Collectors.toSet()))
                    .publishStatus(EnumNoticeBroadCastStatus.PUBLISHED.getCode())
                    .build();
            broadCastFeign.insertOrUpdate(updateStatus);
        }
    }

    @Override
    public void broadcastRepealPush(NoticeBroadCastPushReq req) throws Exception {
        NoticeBroadCastFeignReq feignReq = new NoticeBroadCastFeignReq();
        feignReq.setIds(Stream.of(req.getId()).collect(Collectors.toSet()));
        BaseResult list = broadCastFeign.list(feignReq);

        if (!list.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("没找到广播信息");
        }

        Map<String, Object> extra = new HashMap<>(1);
        extra.put("senderName", Constants.SYSTEM);

        Map<String, Object> content = new HashMap<>(1);
        content.put("broadcaseId", req.getId());
        NoticeMessageReq messageReq = NoticeMessageReq.builder()
                .action(Constants.MessageAction.ACTION_1002)
                .content(content)
                .extra(extra)
                .sender(Constants.SYSTEM)
                .receiver(Constants.SYSTEM)
                .build();
        //发送消息
        push(messageReq);

        //当前用户
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //更新发布状态
        NoticeBroadCastFeignReq updateStatus = NoticeBroadCastFeignReq.builder()
                .ids(Stream.of(req.getId()).collect(Collectors.toSet()))
                .publishStatus(EnumNoticeBroadCastStatus.REPEAL.getCode())
                .repealerUserId(sysUser.getUserId())
                .repealTime(new Date())
                .build();
        broadCastFeign.insertOrUpdate(updateStatus);
    }

    @Override
    public void broadcastPushScheduler() {
        NoticeBroadCastFeignReq build = NoticeBroadCastFeignReq.builder()
                .publishStatus(EnumNoticeBroadCastStatus.NO_PUBLISH.getCode())
                .type(EnumNoticeBroadCastType.TIMING.getCode())
                .build();
        BaseResult list = broadCastFeign.list(build);
        if (!list.isSuccessAndDataNotNull()) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(list.getData()));
        List<NoticeBroadCast> noticeBroadCasts = jsonObject.getJSONArray(BaseResult.LIST).toJavaList(NoticeBroadCast.class);
        if (CollectionUtils.isEmpty(noticeBroadCasts)) {
            return;
        }
        //当前时间
        long currentTimeMillis = System.currentTimeMillis();
        //取得broadcast中的发布时间戳
        Function<NoticeBroadCast, Long> getPublishTimeMillis = broadcast -> {
            String publishTime = broadcast.getPublishTime();
            if (StringUtils.isNotBlank(publishTime)) {
                Date date = DateUtils.formatDate(publishTime, DateUtils.DATE_FORMAT_DEFAULT);
                if (date != null) {
                    return date.getTime();
                }
            }
            return null;
        };
        //待发布的任务
        List<NoticeBroadCast> timings = noticeBroadCasts.parallelStream()
                .filter(broadcast -> EnumNoticeBroadCastType.TIMING.getCode().equals(broadcast.getType()))
                .filter(broadcast -> EnumNoticeBroadCastStatus.NO_PUBLISH.getCode().equals(broadcast.getStatus()))
                .filter(broadcast -> {
                    Long time = getPublishTimeMillis.apply(broadcast);
                    if (time == null) {
                        return false;
                    }
                    return currentTimeMillis >= getPublishTimeMillis.apply(broadcast);
                })
                .collect(Collectors.toList());

        timings.parallelStream()
                .forEach(timing -> {

                    String sc = timing.getSupportCountry();
                    List<String> codeList = strToCodeList.apply(sc);
                    Map<String, Object> content = new HashMap<>();
                    content.put("key", timing.getContent());
                    content.put("html", timing.getHtml());
                    //新增公告ID
                    content.put("broadcaseId", timing.getId());
                    content.put("detailUrl", detailUrl);
                    content.put("supportCountry", codeList);

//                    String sc = timing.getSupportCountry();
//                    List<String> codeList = strToCodeList.apply(sc);
                    Map<String, Object> extra = new HashMap<>(1);
                    extra.put("senderName", Constants.SYSTEM);
//                    extra.put("supportCountry", codeList);

                    NoticeMessageReq messageReq = NoticeMessageReq.builder()
                            .action(Constants.MessageAction.ACTION_1001)
                            .sender(Constants.SYSTEM)
                            .receiver(Constants.SYSTEM)
                            .title(timing.getTitle())
                            .content(content)
                            .extra(extra)
                            .build();

                    try {
                        push(messageReq);
                    } catch (Exception e) {
                        log.error("调用push方法异常", e);
                    }

                    //更新发布状态
                    NoticeBroadCastFeignReq updateStatus = NoticeBroadCastFeignReq.builder()
                            .ids(Stream.of(timing.getId()).collect(Collectors.toSet()))
                            .publishStatus(EnumNoticeBroadCastStatus.PUBLISHED.getCode())
                            .publishTime(new Date())
                            .build();
                    broadCastFeign.insertOrUpdate(updateStatus);
                });
    }

    @Override
    public String uploadBroadcast(MultipartFile multipartFile) {
        // String filePath = FileSlicesUploadUtils.slicesUpload(multipartFile, EnumRelateType.ADMIN_BROADCAST.getCode());

        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_BROADCAST);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return PropertiesUtil.getFileViewUrl(filePath);
    }

    private void push(NoticeMessageReq req) throws Exception {
        if (StringUtils.isBlank(req.getAction())) {
            throw LxtxBizException.newException("消息类型不能为空");
        }
        if (StringUtils.isBlank(req.getSender())) {
            throw LxtxBizException.newException("消息发送者不能为空");
        }
//        if (StringUtils.isBlank(req.getTitle())) {
//            throw LxtxBizException.newException("消息标题不能为空");
//        }
        if (MapUtils.isEmpty(req.getContent())) {
            req.setContent(new HashMap<>());
        }
        if (MapUtils.isEmpty(req.getExtra())) {
            req.setExtra(new HashMap<>());
        }

        FeignSendMsgReq msgReq = createSendMsg.apply(req);
        BaseResult result = MessageUtil.sendBroadcastMsg(msgReq);
        if (!result.isSuccess()) {
            throw LxtxBizException.newException(result.getCode(), result.getMsg());
        }
    }

    private Function<NoticeMessageReq, FeignSendMsgReq> createSendMsg = req -> {
        FeignMessageReq feign = new FeignMessageReq();
        feign.setAction(req.getAction());
        feign.setSender(req.getSender());
        feign.setTitle(req.getTitle());
        feign.setReceiver(req.getReceiver());
        feign.setContent(JSONObject.toJSONString(req.getContent()));
        feign.setExtra(JSONObject.toJSONString(req.getExtra()));

        FeignSendMsgReq yunXinSendMsgReq = new FeignSendMsgReq();
        yunXinSendMsgReq.setBody(feign);
        yunXinSendMsgReq.setOpe(0);
        yunXinSendMsgReq.setFrom(req.getSender());
        yunXinSendMsgReq.setTo(req.getReceiver());
        FeignMessageOption messageOption = new FeignMessageOption();

        messageOption.setPersistent(true);
        yunXinSendMsgReq.setMessageOption(messageOption);
        return yunXinSendMsgReq;
    };


    //取得所有的国际简码
    private List<GlobalCodeResp> getGlobalCodeAllList(List<String> codes) {
        FeignGlobalCodeByCountryReq countryReq = new FeignGlobalCodeByCountryReq();
        countryReq.setCountryCodes(codes);
        BaseResult globalCodeBaseResult = globalCodeFeign.list(countryReq);

        List<GlobalCodeResp> globalCodeRespList = new ArrayList<>();
        if (globalCodeBaseResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(globalCodeBaseResult.getData()));
            globalCodeRespList = JSONObject.parseArray(jsonObject.getString(BaseResult.LIST), GlobalCodeResp.class);
        }
        return globalCodeRespList;
    }

    //将国家简码字符串转成简码列表
    private Function<String, List<String>> strToCodeList = sc -> {
        //支持的国家简码
        List<String> cts = new ArrayList<>();
        if (StringUtils.isNotBlank(sc) && StringUtils.startsWith(sc, "[") && StringUtils.endsWith(sc, "]")) {
            cts = JSONArray.parseObject(sc, new TypeReference<List<String>>() {
            });
        }
        return cts;
    };

    //将国家简码列表转成国家名称
    private Function<List<String>, List<String>> codeListToNameList = cts -> {

        List<GlobalCodeResp> globalCodes = getGlobalCodeAllList(cts);
        //支持的国家名称
        List<String> supportCountryNames = Optional.ofNullable(globalCodes).orElse(new ArrayList<>())
                .parallelStream()
                .map(GlobalCodeResp::getCnName)
                .collect(Collectors.toList());
        return supportCountryNames;
    };

    private Function<BaseResult, NoticeBroadCast> baseListToObj = br -> {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(br.getData()));
        List<NoticeBroadCast> noticeBroadCasts = jsonObject.getJSONArray(BaseResult.LIST).toJavaList(NoticeBroadCast.class);

        if (CollectionUtils.isEmpty(noticeBroadCasts)) {
            return null;
        }

        return noticeBroadCasts.get(0);
    };
}
