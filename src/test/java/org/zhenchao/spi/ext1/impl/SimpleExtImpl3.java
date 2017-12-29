package org.zhenchao.spi.ext1.impl;

import org.zhenchao.spi.ext1.SimpleExt;

public class SimpleExtImpl3 implements SimpleExt {

    @Override
    public String echo(int pt, String s) {
        return "Ext1Impl3-echo";
    }

    @Override
    public String yell(int pt, String s) {
        return "Ext1Impl3-yell";
    }

    @Override
    public String bang(int pt, int i) {
        return "bang3";
    }

}