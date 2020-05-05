package com.lxtx.framework.common.beanself;

/**
 * 注入对象自身
 * @since 2019-07-19
 */
public interface BeanSelfAware {

    void setSelf(Object proxyBean);

}
