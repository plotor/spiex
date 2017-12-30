package org.zhenchao.spi.adaptive;

import org.zhenchao.spi.Adaptive;
import org.zhenchao.spi.SPI;

@SPI
public interface AdaptiveExt {

    @Adaptive()
    String echo(int pt, String s);
}
