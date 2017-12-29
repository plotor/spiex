package org.zhenchao.spi.adaptive.impl;

import org.zhenchao.spi.Adaptive;
import org.zhenchao.spi.ExtensionLoader;
import org.zhenchao.spi.adaptive.HasAdaptiveExt;

@Adaptive
public class HasAdaptiveExt_ManualAdaptive implements HasAdaptiveExt {

    @Override
    public String echo(int pt, String s) {
        HasAdaptiveExt addExt1 = ExtensionLoader.getExtensionLoader(HasAdaptiveExt.class).getExtension("impl1");
        return addExt1.echo(pt, s);
    }
}