package com.lxtx.framework.common.utils.email;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CXM
 */
@Slf4j
@Component
public class EmailUtil {
    /**
     * sendinblue的api链接
     */
    private static final String SEND_IN_BLUE_URL = "https://api.sendinblue.com/v2.0";

    /**
     *
     * @param apiKey sendinblue的api key
     * @param receiverMail
     * @param ccMail
     * @param subject
     * @param content
     * @return
     */
    public String sendEmail(String apiKey, String receiverMail, String ccMail, String subject,  String content) {

        Mailin http = new Mailin(SEND_IN_BLUE_URL, apiKey);
        //设置接收人
        Map<String, String> to = receiverMap(receiverMail);
        //设置抄送人
        Map<String, String> cc = receiverMap(ccMail);
        //设置邮件参数
        Map<String, Object> data = sendDataMap(to, cc, subject, content);
        String result = http.send_email(data);
        log.info("邮箱发送返回的参数：" + result);
        return result;
    }

    /**
     * 配置发送邮件的参数
     *
     * @param to 接收人邮箱
     * @param cc 抄送人邮箱
     * @param subject 邮件主题
     * @return
     */
    public Map< String, Object > sendDataMap(Map<String, String> to, Map<String, String> cc, String subject, String content) {
        //设置header
        Map < String, String > headers = new HashMap <> (0);
        headers.put("Content-Type", "text/html; charset=iso-8859-1");
        headers.put("X-Mailin-custom", "my custom value");
        headers.put("X-Mailin-IP", "102.102.1.2");
        headers.put("X-Mailin-Tag", "LXTX tag");

        //设置要发送的邮箱参数
        Map < String, Object > data = new HashMap <> (0);
        //接收人
        data.put("to", to);
        //抄送人
        data.put("cc", cc);
        //对方回复我们的接受邮箱，修改数组，第一个参数是邮箱地址，第二个参数是备注名
        data.put("replyto", new String [] {"replyto@email.com","LXTX管理员"});
        //必须要配置发件人的邮箱和地址
        data.put("from", new String [] {"from@email.com","LXTX管理员"});
        //邮件主题
        data.put("subject", subject);
        //邮件内容为html
        data.put("html", content);
        //如果没有设置html，则以text为内容
        data.put("text", content);
        //设置头
        data.put("headers", headers);
        //附件
        data.put("attachment", new String [] {});
        //图片
        data.put("inline_image", new String [] {});

        return data;
    }


    /**
     * 接收的邮箱
     *
     * @param email
     * @return
     */
    public Map< String, String > receiverMap(String email) {
        String[] emailStr;
        Map< String, String > receiver = new HashMap<>(0);
        if (StringUtils.isNotBlank(email)) {
            emailStr = email.split(",");
            for (String mail: emailStr) {
                receiver.put(mail, mail);
            }

        }
        return receiver;
    }

    /**
     * 隐藏邮箱帐号的一部分
     * @param emailNumber
     * @return
     */
	public static String hideEmailNo(String emailNumber) {
		String hiddenEmail = emailNumber.replaceAll("(\\w?)(\\w+)(\\w{4})(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1$2****$4");
		return hiddenEmail;
	}

}
