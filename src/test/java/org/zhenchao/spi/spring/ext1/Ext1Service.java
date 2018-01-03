package org.zhenchao.spi.spring.ext1;

import org.zhenchao.spi.Adaptive;
import org.zhenchao.spi.DIoC;

/**
 * @author zhenchao.wang 2018-01-01 14:33
 * @version 1.0.0
 */
@DIoC
public interface Ext1Service {

    @Adaptive(mapping = {"1=demoServiceImpl1", "2=demoServiceImpl2"})
    String hello(int pt, String name);

}
