package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.im.admin.feign.feign.CheckAccountNameFeign;
import com.lxtx.im.admin.feign.request.CheckName;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter

public class CheckAccountNameFeignFallback implements CheckAccountNameFeign {
    private Throwable cause;

    @Override
    public String selectByUser(CheckName user) {
        log.error("feign", cause);
        return null;
    }
}
