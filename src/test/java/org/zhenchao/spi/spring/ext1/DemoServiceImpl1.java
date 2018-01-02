package org.zhenchao.spi.spring.ext1;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author zhenchao.wang 2018-01-01 14:35
 * @version 1.0.0
 */
@Service("demoServiceImpl1")
@Lazy(false)
public class DemoServiceImpl1 implements DemoService {

    @Override
    public String hello(int pt, String name) {
        System.out.println("pt=" + pt + ", " + this.getClass().getSimpleName() + " say hello to " + name);
        return "DemoServiceImpl1-" + name;
    }
}
