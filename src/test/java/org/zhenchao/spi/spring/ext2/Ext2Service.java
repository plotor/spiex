package org.zhenchao.spi.spring.ext2;

import org.zhenchao.spi.spring.Adaptee;

/**
 * @author zhenchao.wang 2018-01-02 11:06
 * @version 1.0.0
 */
@Adaptee("ext2_service_adapter")
public interface Ext2Service {

    String hello();

}
