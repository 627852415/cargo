package com.lxtx.im.admin.feign.feign;

import com.lxtx.im.admin.feign.feign.factory.CheckAccountNameFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.CheckName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "lxtx-im-core", fallbackFactory = CheckAccountNameFeignFallbackFactory.class)
public interface CheckAccountNameFeign {

    /**
     * 取得商户姓名
     *
     * @param user
     * @return
     */
    @RequestMapping("/check/selectByUser")
    @ResponseBody
    String selectByUser(@RequestBody CheckName user);
}
