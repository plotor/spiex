package org.zhenchao.spi.spring;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.zhenchao.spi.spring.ext3.Ext3Service;
import org.zhenchao.spi.support.FactorResolver;

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

    @Autowired
    @Qualifier("Ext3Service-adapter")
    private Ext3Service ext3Service;

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

    @Test
    public void typeLevelAdaptive() throws Exception {
        Assert.assertEquals("Ext3ServiceImpl1-one", ext3Service.one("a"));
        Assert.assertEquals("Ext3ServiceImpl2-one", ext3Service.one("b"));

        FactorResolver resolver = new FactorResolver() {
            @Override
            public String resolve(Object arg) {
                Integer i = (Integer) arg;
                if (1 == i) return "a";
                if (2 == i) return "b";
                return null;
            }
        };
        Assert.assertEquals("Ext3ServiceImpl1-two", ext3Service.two(RandomStringUtils.randomAlphabetic(8), 1, resolver));
        Assert.assertEquals("Ext3ServiceImpl2-two", ext3Service.two(RandomStringUtils.randomAlphabetic(8), 2, resolver));

        Assert.assertEquals("Ext3ServiceImpl1-three", ext3Service.three(1));
        Assert.assertEquals("Ext3ServiceImpl2-three", ext3Service.three(2));

        Assert.assertEquals("Ext3ServiceImpl1-four", ext3Service.four(RandomStringUtils.randomAlphabetic(8), 1));
        Assert.assertEquals("Ext3ServiceImpl2-four", ext3Service.four(RandomStringUtils.randomAlphabetic(8), 2));

        try {
            ext3Service.five();
            Assert.fail();
        } catch (Throwable e) {
            Assert.assertTrue(e instanceof UnsupportedOperationException);
        }

    }

    private String createAdapterBeanName(Class<?> clazz) {
        if (!clazz.isInterface() || !clazz.isAnnotationPresent(Adaptee.class)) {
            throw new IllegalArgumentException("adaptee must be interface and annotated with @Adaptee : " + clazz.getName());
        }
        Adaptee adaptee = clazz.getAnnotation(Adaptee.class);
        return StringUtils.isNotBlank(adaptee.value()) ? adaptee.value().trim() : clazz.getSimpleName() + "-adapter";
    }

}
