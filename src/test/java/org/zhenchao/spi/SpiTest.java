package org.zhenchao.spi;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.zhenchao.spi.adaptive.HasAdaptiveExt;
import org.zhenchao.spi.adaptive.impl.HasAdaptiveExt_ManualAdaptive;
import org.zhenchao.spi.ext1.SimpleExt;

/**
 * @author zhenchao.wang 2017-12-29 16:55
 * @version 1.0.0
 */
public class SpiTest {

    @Test
    public void test_useAdaptiveClass() throws Exception {
        ExtensionLoader<HasAdaptiveExt> loader = ExtensionLoader.getExtensionLoader(HasAdaptiveExt.class);
        HasAdaptiveExt ext = loader.getAdaptiveExtension();
        assertTrue(ext instanceof HasAdaptiveExt_ManualAdaptive);
    }

    @Test
    public void test_getAdaptiveExtension_defaultAdaptiveKey() throws Exception {
        try {
            SimpleExt ext = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
            ext.echo(1, "haha");
            Assert.fail();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        SimpleExt ext = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
        String echo = ext.echo(2, "haha");
        assertEquals("Ext1Impl2-echo", echo);
    }

}
