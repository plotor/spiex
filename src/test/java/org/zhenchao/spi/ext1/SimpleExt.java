package org.zhenchao.spi.ext1;

import org.zhenchao.spi.Adaptive;
import org.zhenchao.spi.SPI;

@SPI("impl1")
public interface SimpleExt {

    @Adaptive
    String echo(int pt, String s);

    @Adaptive(index = 0, mapping = {"1=impl1", "2=impl2", "3=impl3"})
    String yell(int pt, String s);

    // 无@Adaptive
    String bang(int pt, int i);

}