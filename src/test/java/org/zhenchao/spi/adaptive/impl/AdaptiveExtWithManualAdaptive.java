package org.zhenchao.spi.adaptive.impl;

import org.zhenchao.spi.Adaptive;
import org.zhenchao.spi.ExtensionLoader;
import org.zhenchao.spi.adaptive.AdaptiveExt;

@Adaptive
public class AdaptiveExtWithManualAdaptive implements AdaptiveExt {

    @Override
    public String echo(int pt, String s) {
        AdaptiveExt addExt1 = ExtensionLoader.getExtensionLoader(AdaptiveExt.class).getExtension("impl1");
        return addExt1.echo(pt, s);
    }
}