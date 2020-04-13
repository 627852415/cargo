package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.soa.HttpInvoker;
import com.lxtx.im.admin.service.RefreshScopeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Czh
 * Date: 2018/10/19 下午1:42
 */
@Slf4j
@Service
public class RefreshScopeServiceImpl implements RefreshScopeService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public BaseResult refreshScope(String serviceName) {

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        for (ServiceInstance instance : instances) {
            Post post = new Post(instance.getUri() + "/actuator/refresh");
            post.setConnectionRequestTimeout(10 * 1000);
            String body = HttpInvoker.execute(post).getBody();
            log.info("应用名称：{},host: {},配置刷新结果：{}",serviceName,instance.getUri(),body);
        }
        return BaseResult.success();

    }

    @Override
    public BaseResult getServiceList() {

        List<String> services = discoveryClient.getServices();
        return BaseResult.success(services);
    }
}