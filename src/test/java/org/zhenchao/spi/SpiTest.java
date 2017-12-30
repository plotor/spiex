package org.zhenchao.spi;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.zhenchao.spi.adaptive.AdaptiveExt;
import org.zhenchao.spi.adaptive.impl.AdaptiveExtWithManualAdaptive;
import org.zhenchao.spi.ext1.SimpleExt;
import org.zhenchao.spi.ext2.ExtWithInject;
import org.zhenchao.spi.ext3.WrappedExt;
import org.zhenchao.spi.ext4.Element;
import org.zhenchao.spi.ext4.ElementFactorResolver;
import org.zhenchao.spi.ext4.ExtWithFactorResolver;
import org.zhenchao.spi.support.FactorResolver;

/**
 * @author zhenchao.wang 2017-12-29 16:55
 * @version 1.0.0
 */
public class SpiTest {

    @Test
    public void manualAdaptiveClass() throws Exception {
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt ext = loader.getAdaptiveExtension();
        assertTrue(ext instanceof AdaptiveExtWithManualAdaptive);
    }

    @Test
    public void adaptiveExtension() throws Exception {
        // 没有指定决策因子，使用默认的
        SimpleExt ext = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
        assertEquals("Ext1Impl1-one", ext.one(2, "hello"));

        // 默认以第一个参数作为决策因子
        assertEquals("Ext1Impl1-two", ext.two(1, "hello"));
        assertEquals("Ext1Impl2-two", ext.two(2, "hello"));
        assertEquals("Ext1Impl3-two", ext.two(3, "hello"));
        try {
            ext.two(0, "hello");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // 指定以第二个参数作为决策因子
        assertEquals("Ext1Impl1-three", ext.three(1, "a"));
        assertEquals("Ext1Impl2-three", ext.three(2, "b"));
        assertEquals("Ext1Impl3-three", ext.three(3, "c"));
        try {
            ext.three(1, "hello");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // 无@Adaptive注解
        try {
            ext.four(1, "a");
            Assert.fail();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void adaptiveExtensionWithInject() throws Exception {
        ExtWithInject ext = ExtensionLoader.getExtensionLoader(ExtWithInject.class).getExtension("impl1");
        assertEquals("ExtWithInjectImpl1-echo", ext.echo(1, "hello"));
    }

    @Test
    public void wrapperExtension() throws Exception {
        WrappedExt ext1 = ExtensionLoader.getExtensionLoader(WrappedExt.class).getExtension("impl1");
        ext1.echo(1, "hello");

        WrappedExt ext2 = ExtensionLoader.getExtensionLoader(WrappedExt.class).getExtension("impl2");
        ext2.echo(1, "hello");
    }

    @Test
    public void factorResolver() throws Exception {
        ExtWithFactorResolver ext = ExtensionLoader.getExtensionLoader(ExtWithFactorResolver.class)
                .setFactorResolver(new ElementFactorResolver()).getAdaptiveExtension();

        assertEquals("FactorExtImpl1-one", ext.one(null, "hello"));
        assertEquals("FactorExtImpl2-two", ext.two(new Element("zhenchao", 2), "hello", new FactorResolver() {
            @Override
            public String resolve(Object arg) {
                if(arg instanceof Element) {
                    return String.valueOf(((Element) arg).getAge());
                }
                throw new IllegalArgumentException("not element type");
            }
        }));
        assertEquals("FactorExtImpl3-three", ext.three(new Element("c", 1), "hello"));

    }

}
