package org.zhenchao.spi.ext1.impl;

import org.zhenchao.spi.ext1.SimpleExt;

public class SimpleExtImpl2 implements SimpleExt {

    @Override
    public String echo(int pt, String s) {
        return "Ext1Impl2-echo";
    }

    @Override
    public String yell(int pt, String s) {
        return "Ext1Impl2-yell";
    }

    @Override
    public String bang(int pt, int i) {
        return "bang2";
    }

}