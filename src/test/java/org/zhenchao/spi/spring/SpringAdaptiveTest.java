package org.zhenchao.spi.spring;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhenchao.wang 2018-01-01 14:38
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class SpringAdaptiveTest {

    @Autowired
    @Qualifier("DemoService-adapter")
    private DemoService demoService;

    @Test
    public void getAdaptiveInstance() throws Exception {
        // DemoService demoService = applicationContext.getBean(this.createAdapterBeanName(DemoService.class), DemoService.class);
        Assert.assertEquals("DemoServiceImpl1-zhenchao", demoService.hello(1, "zhenchao"));
        Assert.assertEquals("DemoServiceImpl2-zhenchao", demoService.hello(2, "zhenchao"));
    }

    private String createAdapterBeanName(Class<?> clazz) {
        if (!clazz.isInterface() || !clazz.isAnnotationPresent(Adaptee.class)) {
            throw new IllegalArgumentException("adaptee must be interface and annotated with @Adaptee : " + clazz.getName());
        }
        Adaptee adaptee = clazz.getAnnotation(Adaptee.class);
        return StringUtils.isNotBlank(adaptee.value()) ? adaptee.value().trim() : clazz.getSimpleName() + "-adapter";
    }

}
