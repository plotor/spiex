package org.zhenchao.spi.adaptive.impl;

import org.zhenchao.spi.adaptive.AdaptiveExt;

public class AdaptiveExtImpl1 implements AdaptiveExt {

    @Override
    public String echo(int pt, String s) {
        return this.getClass().getSimpleName();
    }

}