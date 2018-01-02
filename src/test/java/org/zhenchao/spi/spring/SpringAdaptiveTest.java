package org.zhenchao.spi.spring;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zhenchao.spi.spring.ext1.DemoService;
import org.zhenchao.spi.spring.ext2.Ext2Service;

/**
 * @author zhenchao.wang 2018-01-01 14:38
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class SpringAdaptiveTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("DemoService-adapter")
    private DemoService demoService;

    @Autowired
    @Qualifier("ext2_service_adapter")
    private Ext2Service ext2Service;

    @Test
    public void display() throws Exception {
        String[] names = applicationContext.getBeanDefinitionNames();
        System.out.println("bean names : \n" + StringUtils.join(names, "\n"));
    }

    @Test
    public void adaptiveInstance() throws Exception {
        // DemoService demoService = applicationContext.getBean(this.createAdapterBeanName(DemoService.class), DemoService.class);
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals("DemoServiceImpl1-zhenchao", demoService.hello(1, "zhenchao"));
            Assert.assertEquals("DemoServiceImpl2-zhenchao", demoService.hello(2, "zhenchao"));
        }
    }

    @Test
    public void manualAdaptiveInstance() throws Exception {
        Assert.assertEquals("ManualExt2Service", ext2Service.hello());
    }

    private String createAdapterBeanName(Class<?> clazz) {
        if (!clazz.isInterface() || !clazz.isAnnotationPresent(Adaptee.class)) {
            throw new IllegalArgumentException("adaptee must be interface and annotated with @Adaptee : " + clazz.getName());
        }
        Adaptee adaptee = clazz.getAnnotation(Adaptee.class);
        return StringUtils.isNotBlank(adaptee.value()) ? adaptee.value().trim() : clazz.getSimpleName() + "-adapter";
    }

}
