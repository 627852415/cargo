package com.lxtx.im.admin.web.config;

import com.lxtx.framework.common.interceptor.LocaleInterceptor;
import com.lxtx.framework.common.log.interceptor.LoggerInterceptor;
import com.lxtx.framework.common.security.interceptor.CheckSignatureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * mvc 配置
 *
 * @author zkj
 * @date 2018-08-07
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	private LoggerInterceptor loggerInterceptor;
	@Autowired
	private CheckSignatureInterceptor checkSignatureInterceptor;
	@Autowired
	private LocaleInterceptor localeInterceptor;
    @Autowired
    private CheckUsbTokenInterceptor checkUsbTokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
		// 多语言拦截器
		registry.addInterceptor(localeInterceptor).addPathPatterns("/**");
		registry.addInterceptor(checkSignatureInterceptor).addPathPatterns("/**").excludePathPatterns("/callback/**");

        // usbToken拦截器
        registry.addInterceptor(checkUsbTokenInterceptor).addPathPatterns(
        		"/manage/login"
                ,"/user/modify"
                ,"/user/resetPsd"
                ,"/user/operateState"
				//,"/coin/edit"
				,"/coin/save"
                ,"/member/status/turn"
                //,"/coinCharge/add"
                //,"/coinCharge/info"
                ,"/coinCharge/save"
                ,"/coinCharge/update"
                ,"/coinCharge/delete"
                ,"/withdraw/apply/fail/dealFail"
                ,"/withdraw/apply/fail/dealSuccess"
                ,"/dict/notice/save"
                //,"/dict/transaction/coin/update"
                ,"/dict/save"
                ,"/dict/delete"
                ,"/otc/bankcard/bind"
                ,"/otc/bankcard/unbind"
                //,"/platform/withdraw/config/otc/limit/edit"
                ,"/merchant/update/status"
                ,"/billcheck/pre/edit"
                ,"/billcheck/edit"
                ,"/sys/user/add"//管理员列表-新增用户
                ,"/sys/user/modify"//管理员列表-修改
                ,"/sys/user/resetPwd"//管理员列表-密码重置
                ,"/sys/user/delete"//管理员列表-删除
                ,"/sys/user/disable"//管理员列表-禁用
                ,"/sys/user/enable"//管理员列表-启用
                ,"/sys/menu/update"//菜单管理-编辑
                ,"/sys/menu/delete/one"//菜单管理-删除
                ,"/quartz/stop"//任务管理-停止
                ,"/quartz/save"//任务管理-新增
                ,"/quartz/delete"//任务管理-删除
                ,"/notification/reissue/handle"//钱包管理-通知重发
                ,"/game/save"//游戏管理-编辑
                ,"/platform/withdraw/config/save"//OTC管理-平台提款配置-编辑
                ,"/platform/withdraw/config/delete"//OTC管理-平台提款配置-删除
                ,"/otc/withdraw/config/saveOrUpdate"//OTC管理-OTC提现配置-	编辑
                ,"/otc/withdraw/config/delete"//OTC管理-OTC提现配置-删除
                ,"/inviteRelation/addRelation"//邀请注册管理-邀请关系管理-添加下级
                ,"/legal/coin/save"//钱包模式管理-法币管理-编辑
                ,"/legal/coin/del"//钱包模式管理-法币管理-删除
                ,"/sys/user/disable"//群列表-解散群
                ,"/group/information/flag"//群列表-私聊开放 关闭
                ,"/merchant/deposit/save"//换汇-信用值管理-新增-编辑
                ,"/merchant/deposit/del"//换汇-信用值管理-删除
                ,"/exchange/merchant/update/status"//换汇-换汇商家管理-启用 禁用
                ,"/offsite/exchange/goods/down"//换汇-商品管理-下架
                ,"/offsite/exchange/complaint/completed"//换汇-投诉处理
                ,"/offsite/exchange/complaint/record/save"//换汇-保存
                ,"/offsite/exchange/rebate/save"//换汇-支付方式-编辑
                ,"/offsite/exchange/wave/rate/save"//换汇-汇率管理-编辑
                ,"/offsite/exchange/wave/rate/areaUpdate"//换汇-汇率管理-地区设置
                ,"/offsite/exchange/wave/rate/del"//换汇-汇率管理-删除

        );
//                .excludePathPatterns(
//		        "/manage/toLogin"
//                ,"/**/*.js"
//                ,"/**/*.css"
//        );

        registry.addInterceptor(localeChangeInterceptor());
	}

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

}
