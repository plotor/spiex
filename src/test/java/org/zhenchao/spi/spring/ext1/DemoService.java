package org.zhenchao.spi.spring.ext1;

import org.zhenchao.spi.Adaptive;
import org.zhenchao.spi.spring.Adaptee;

/**
 * @author zhenchao.wang 2018-01-01 14:33
 * @version 1.0.0
 */
@Adaptee
public interface DemoService {

    @Adaptive(mapping = {"1=demoServiceImpl1", "2=demoServiceImpl2"})
    String hello(int pt, String name);

}
