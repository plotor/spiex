package org.zhenchao.spi.spring;

import org.springframework.stereotype.Service;

/**
 * @author zhenchao.wang 2018-01-01 14:36
 * @version 1.0.0
 */
@Service("demoServiceImpl2")
public class DemoServiceImpl2 implements DemoService {

    @Override
    public String hello(int pt, String name) {
        System.out.println("pt=" + pt + ", " + this.getClass().getSimpleName() + " say hello to " + name);
        return "DemoServiceImpl2-" + name;
    }

}
