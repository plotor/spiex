package org.zhenchao.spi.spring;

import org.zhenchao.spi.Adaptive;

/**
 * @author zhenchao.wang 2018-01-01 14:33
 * @version 1.0.0
 */
@Adaptee
public interface DemoService {

    @Adaptive(mapping = {"1=demoServiceImpl1", "2=demoServiceImpl2"})
    String hello(int pt, String name);

}
