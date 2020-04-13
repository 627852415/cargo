package com.lxtx.im.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.SmsUtil;
import com.lxtx.framework.common.utils.TelegramUtil;
import com.lxtx.framework.common.utils.email.EmailUtil;
import com.lxtx.im.admin.dao.SysLogDao;
import com.lxtx.im.admin.dao.model.SysLog;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.SysLogService;
import com.lxtx.im.admin.service.enums.EnumNoticeType;
import com.lxtx.im.admin.service.request.SysLogListPageReq;
import com.lxtx.im.admin.service.response.SysLogListPageResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.lxtx.framework.common.utils.DateUtils.DATE_FORMAT_DEFAULT;

@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private DictService dictService;
    @Autowired
    private TelegramUtil telegramUtil;
    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private EmailUtil emailUtil;

    @Override
    public BaseResult listPage(SysLogListPageReq req) {
        EntityWrapper<SysLog> ew = new EntityWrapper<>();
        if (StringUtils.isNotBlank(req.getOperation())) {

        }
        if (StringUtils.isNotBlank(req.getIp())) {
            ew.like(SysLog.IP, req.getIp());
        }
        if (StringUtils.isNotBlank(req.getOperation())) {
            ew.like(SysLog.OPERATION, req.getOperation());
        }
        if (StringUtils.isNotEmpty(req.getUsername())) {
            ew.like("username", req.getUsername());
        }
        if (StringUtils.isNotEmpty(req.getCreateTimeStart())) {
            ew.ge("create_time", req.getCreateTimeStart());
        }
        if (StringUtils.isNotEmpty(req.getCreateTimeEnd())) {
            ew.le("create_time", req.getCreateTimeEnd());
        }
        SysLogListPageResp sysLogListPageResp = new SysLogListPageResp();
        ew.orderBy("create_time", false);
        Page<SysLog> page = sysLogDao.selectPage(req.getPage(), ew);
        BeanUtils.copyProperties(page, sysLogListPageResp);
        return BaseResult.success(sysLogListPageResp);
    }

    @Override
    public void sendNotice(SysLog sysLog) {
        //获取管理员配置的通知方式
        String domain = DictConstants.DICT_DOMAIN_SYSTEM_MANAGER;
        String[] noticeType = getNoticeType(domain);

        //根据不同类型发送不同通知
        Arrays.stream(noticeType).forEach(type -> {
            //短信通知
            if (EnumNoticeType.MESSAGE.getCode().equals(type)) {
                String contentMessage = getTelegramMessageContent(sysLog);
                String[] phones = new String[0];
                String telephone = dictService.getDictValue(domain, DictConstants.TELEPHONE);
                if (StringUtils.isNotBlank(telephone)) {
                    phones = telephone.split(",");
                }
                Arrays.stream(phones).forEach(phone -> smsUtil.sendPhoneMessageSG(phone, contentMessage));
            }

            //电报通知
            if (EnumNoticeType.TELEGRAM.getCode().equals(type)) {
                String contentMessage = getTelegramMessageContent(sysLog);
                String botToken = dictService.getDictValue(domain, DictConstants.TELEGRAM_BOT_TOKEN);
                String chatId = dictService.getDictValue(domain, DictConstants.TELEGRAM_CHAT_ID);
                telegramUtil.sendTelegram(botToken, chatId, contentMessage);
            }

            //邮箱通知
            if (EnumNoticeType.EMAIL.getCode().equals(type)) {
                String apiKey = dictService.getDictValue(DictConstants.SEND_IN_BLUE, DictConstants.API_KEY);
                String adminEmail = dictService.getDictValue(domain, DictConstants.EMAIL);
                String cc = dictService.getDictValue(domain, DictConstants.CC);
                emailUtil.sendEmail(apiKey, adminEmail, cc, "管理后台操作监控日志", getEmailContent(sysLog));
            }
        });
    }

    private String getTelegramMessageContent(SysLog sysLog) {
        StringBuffer sb = new StringBuffer("#管理后台操作监控日志：\n");
        sb.append("用户名称：").append(sysLog.getUsername()).append("\n");
        sb.append("用户IP：").append(sysLog.getIp()).append("\n");
        sb.append("操作名称：").append(sysLog.getOperation()).append("\n");
        sb.append("操作时间：").append(DateUtils.getDateFormat(sysLog.getCreateTime(), DATE_FORMAT_DEFAULT)).append("\n");
        sb.append("请求URL：").append(sysLog.getUrl()).append("\n");
        sb.append("请求方法：").append(sysLog.getMethod()).append("\n");
        if (StringUtils.isNotBlank(sysLog.getParams())) {
            sb.append("请求参数：").append(sysLog.getParams()).append("\n");
        }
        return sb.toString();
    }

    private String getEmailContent(SysLog sysLog) {
        String endH3 = "</h3>";
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title></title></head>");
        sb.append("<body><h3>管理后台操作监控日志！</h3>");
        sb.append("<h3>用户名称：").append(sysLog.getUsername()).append(endH3);
        sb.append("<h3>用户IP：").append(sysLog.getIp()).append(endH3);
        sb.append("<h3>操作名称：").append(sysLog.getOperation()).append(endH3);
        sb.append("<h3>操作时间：").append(DateUtils.getDateFormat(sysLog.getCreateTime(), DATE_FORMAT_DEFAULT)).append(endH3);
        sb.append("<h3>请求URL：").append(sysLog.getUrl()).append(endH3);
        sb.append("<h3>请求方法：").append(sysLog.getMethod()).append(endH3);
        if (StringUtils.isNotBlank(sysLog.getParams())) {
            sb.append("<h3>请求参数：").append(sysLog.getParams()).append(endH3);
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    private String[] getNoticeType(String domain) {
        String noticeType = dictService.getDictValue(domain, DictConstants.TYPE);
        if (StringUtils.isNotBlank(noticeType)) {
            return noticeType.split(",");
        }
        return new String[0];
    }

}
