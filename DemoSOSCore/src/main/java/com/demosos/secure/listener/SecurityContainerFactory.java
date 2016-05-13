package com.demosos.secure.listener;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

import java.util.Properties;

/**
 * @author DemoSOS
 */
public class SecurityContainerFactory extends CacheEventListenerFactory {

    @Override
    public CacheEventListener createCacheEventListener(Properties arg0) {
        return new SecurityContainerListener();
    }

}
