package org.zhenchao.spi;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.zhenchao.spi.adaptive.AdaptiveExt;
import org.zhenchao.spi.adaptive.impl.AdaptiveExtWithManualAdaptive;
import org.zhenchao.spi.ext1.SimpleExt;

/**
 * @author zhenchao.wang 2017-12-29 16:55
 * @version 1.0.0
 */
public class SpiAdaptiveTest {

    @Test
    public void manualAdaptiveClass() throws Exception {
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt ext = loader.getAdaptiveExtension();
        assertTrue(ext instanceof AdaptiveExtWithManualAdaptive);
    }

    @Test
    public void getAdaptiveExtension() throws Exception {
        // 没有指定决策因子，使用默认的
        SimpleExt ext = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
        assertEquals("Ext1Impl1-one", ext.one(2, "haha"));

        // 默认以第一个参数作为决策因子
        assertEquals("Ext1Impl1-two", ext.two(1, "haha"));
        assertEquals("Ext1Impl2-two", ext.two(2, "haha"));
        assertEquals("Ext1Impl3-two", ext.two(3, "haha"));
        try {
            ext.two(0, "haha");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // 指定以第二个参数作为决策因子
        assertEquals("Ext1Impl1-three", ext.three(1, "a"));
        assertEquals("Ext1Impl2-three", ext.three(2, "b"));
        assertEquals("Ext1Impl3-three", ext.three(3, "c"));
        try {
            ext.three(1, "haha");
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

}
