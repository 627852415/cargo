package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeUserStatisticsFeign;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeWaveRateFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.FeignExchangeUserStatisticsListPageReq;
import com.lxtx.im.admin.feign.request.FeignMemberListReq;
import com.lxtx.im.admin.feign.request.FeignQueryUserListReq;
import com.lxtx.im.admin.service.OffsiteExchangeUserStatisticsService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.ExchangeUserStatisticsListPageReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeUserStatisticsDownloadResp;
import com.lxtx.im.admin.service.response.OffsiteExchangeUserStatisticsListPageResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户换汇统计业务类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@Service
public class OffsiteExchangeUserStatisticsServiceImpl implements OffsiteExchangeUserStatisticsService {

    private static final String OFFSITE_EXCHANGE_USER_STATISTICS_NAME = "用户换汇统计";

    @Autowired
    private OffsiteExchangeUserStatisticsFeign offsiteExchangeUserStatisticsFeign;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private OffsiteExchangeWaveRateFeign offsiteExchangeWaveRateFeign;
    @Autowired
    private UserService userService;

    @Override
    public void waveRateList(Model model) {
        BaseResult baseResult = offsiteExchangeWaveRateFeign.list();
        List list = null;
        if(baseResult.isSuccessAndDataNotNull()){
            Map<String, Object> map = (Map<String, Object>) baseResult.getData();
            list = (List) map.get("list");
        }
        model.addAttribute("waveRateList", list);
    }

    @Override
    public void exportExcel(HttpServletResponse response, ExchangeUserStatisticsListPageReq req) {
        req.setSize(100000);
        OffsiteExchangeUserStatisticsListPageResp pageResp = listPage(req);
        if(CollectionUtils.isEmpty(pageResp.getRecords())){
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        List<OffsiteExchangeUserStatisticsDownloadResp> downloadRespList = Lists.newArrayList();
        pageResp.getRecords().forEach(dto -> {
            OffsiteExchangeUserStatisticsDownloadResp downloadResp = new OffsiteExchangeUserStatisticsDownloadResp();
            BeanUtils.copyProperties(dto, downloadResp);
            downloadResp.setWaveRate(dto.getSourceCoin()+" => "+dto.getTargetCoin());
            downloadResp.setCompletedAmountCoinName(dto.getTargetCoin());
            downloadResp.setCompletedVolumeCoinName(dto.getSourceCoin());
            downloadResp.setFloatScopePercent(dto.getFloatScopePercent().toPlainString()+"%");
            downloadRespList.add(downloadResp);
        });
        ExcelUtil.exportExcel(response, downloadRespList, OFFSITE_EXCHANGE_USER_STATISTICS_NAME, OFFSITE_EXCHANGE_USER_STATISTICS_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }

    @Override
    public OffsiteExchangeUserStatisticsListPageResp listPage(ExchangeUserStatisticsListPageReq req) {
        OffsiteExchangeUserStatisticsListPageResp resp = new OffsiteExchangeUserStatisticsListPageResp();
        List<String> accountList = null;
        if(StringUtils.isNotBlank(req.getAccount()) || StringUtils.isNotBlank(req.getName()) || StringUtils.isNotBlank(req.getTelephone())){
            FeignMemberListReq userListFeignReq = new FeignMemberListReq();
            BeanUtils.copyProperties(req, userListFeignReq);
            if(StringUtils.isNotBlank(req.getAccount())){
                String[] split = req.getAccount().split(",");
                if(split.length > 1){
                    userListFeignReq.setAccountList(Lists.newArrayList(split));
                    userListFeignReq.setAccount(null);
                }
            }
            BaseResult userListResult = userFeign.list(userListFeignReq);
            if (!userListResult.isSuccessAndDataNotNull()) {
                return resp;
            }
            JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(userListResult.getData()));
            List<UserDetailResp> userListResp = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);
            accountList = userListResp.stream().map(UserDetailResp::getAccount).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(accountList)){
                return resp;
            }
        }
        FeignExchangeUserStatisticsListPageReq feignReq = new FeignExchangeUserStatisticsListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        feignReq.setAccount(accountList);
        feignReq.setShowAccount(userService.isShowAccount());
        BaseResult baseResult = offsiteExchangeUserStatisticsFeign.listPage(feignReq);
        if(baseResult.isSuccessAndDataNotNull()){
            Map map = (Map) baseResult.getData();
            resp = JSON.parseObject(JSON.toJSONString(map), OffsiteExchangeUserStatisticsListPageResp.class);
            List<OffsiteExchangeUserStatisticsListPageResp.OffsiteExchangeUserStatisticsDTO> records = resp.getRecords();
            if(!CollectionUtils.isEmpty(records)){
                accountList = records.stream().map(OffsiteExchangeUserStatisticsListPageResp.OffsiteExchangeUserStatisticsDTO::getAccount).collect(Collectors.toList());
                FeignQueryUserListReq userListFeignReq = new FeignQueryUserListReq();
                userListFeignReq.setIds(accountList);
                BaseResult userListResult = userFeign.queryList(userListFeignReq);
                if(userListResult.isSuccessAndDataNotNull()){
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserListResp.class);
                    Map<String, UserDetailResp> userListMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, Function.identity()));
                    records.forEach(dto -> {
                        UserDetailResp userDetailResp = userListMap.get(dto.getAccount());
                        if(Objects.nonNull(userDetailResp)){
                            dto.setName(userDetailResp.getName());
                            dto.setTelephone(userDetailResp.getFullTelephone().replace(userDetailResp.getTelephone(), "+"+userDetailResp.getTelephone()));
                        }
                    });
                }
            }
            return resp;
        }
        return resp;
    }

}
