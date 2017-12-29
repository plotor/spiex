package org.zhenchao.spi.adaptive.impl;

import org.zhenchao.spi.adaptive.HasAdaptiveExt;

public class HasAdaptiveExtImpl1 implements HasAdaptiveExt {

    @Override
    public String echo(int pt, String s) {
        return this.getClass().getSimpleName();
    }

}