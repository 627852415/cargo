package com.lxtx.im.admin.web.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.PatternCountryFeign;
import com.lxtx.im.admin.feign.feign.PatternWhiteListFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.FeignUserWalletPatternLoadOldDataReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户钱包模式控制器
 *
 * @since 2019-05-09
 */
@Slf4j
@RestController
@RequestMapping("/user/wallet/pattern")
public class UserWalletPatternController {

    @Autowired
    UserFeign userFeign;
    @Autowired
    PatternWhiteListFeign patternWhiteListFeign;
    @Autowired
    WalletUserFeign walletUserFeign;
    @Autowired
    PatternCountryFeign patternCountryFeign;

//    /**
//     * 旧数据迁移  (version=1.4.1， 下一期可移除)
//     *
//     * @return
//     */
//    @SysLogAop("钱包模式-迁移旧数据")
//    @PostMapping("/loadOldData")
//    public BaseResult loadOldData() {
//        log.info("钱包模式-迁移旧数据开始");
//        int current = 1;
//        int max = 1;
//
//        while (max < 1000) {
//            BaseResult baseResult = patternCountryFeign.userPatternsPage(new Page(current, 500));
//
//            if (baseResult.isSuccessAndDataNotNull()) {
//                Map<String, Object> resultMap = (Map<String, Object>)baseResult.getData();
//                List<Map<String, List<Integer>>> list = (List<Map<String, List<Integer>>>)resultMap.get("list");
//
//                log.info("钱包模式-迁移旧数据,current={},size={}", current, list.size());
//
//                if (CollectionUtils.isNotEmpty(list)) {
//                    List<FeignUserWalletPatternLoadOldDataReq.UserWalletPatternLoadOldData> loadOldDataReqList = new ArrayList<>();
//                    list.forEach(map -> {
//                        Set<String> set = map.keySet();
//                        String account = set.iterator().next();
//
//                        FeignUserWalletPatternLoadOldDataReq.UserWalletPatternLoadOldData data = new FeignUserWalletPatternLoadOldDataReq.UserWalletPatternLoadOldData();
//                        data.setAccount(account);
//                        data.setPatterns(map.get(account));
//
//                        loadOldDataReqList.add(data);
//                    });
//
//                    FeignUserWalletPatternLoadOldDataReq feign = new FeignUserWalletPatternLoadOldDataReq();
//                    feign.setList(loadOldDataReqList);
//                    walletUserFeign.loadOldData(feign);
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//
//            max++;
//            current++;
//        }
//
//        log.info("钱包模式-迁移旧数据结束");
//        return BaseResult.success();
//    }

}
