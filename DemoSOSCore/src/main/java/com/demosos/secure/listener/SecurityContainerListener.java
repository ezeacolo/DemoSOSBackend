package com.demosos.secure.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import org.apache.log4j.Logger;

/**
 * @author DemoSOS
 */
public class SecurityContainerListener implements CacheEventListener {

    private static Logger log = Logger.getLogger(SecurityContainerListener.class);

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#dispose()
     */
    public void dispose() {
        log.debug("EHCACHE LISTENER: method -> dispose()");
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#notifyElementEvicted(net.sf.ehcache.Ehcache, net.sf.ehcache.Element)
     */
    public void notifyElementEvicted(Ehcache arg0, Element arg1) {
        log.debug("EHCACHE LISTENER: method -> notifyElementEvicted(Ehcache arg0, Element arg1)");
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#notifyElementExpired(net.sf.ehcache.Ehcache, net.sf.ehcache.Element)
     */
    public void notifyElementExpired(Ehcache arg0, Element arg1) {
        log.debug("EHCACHE LISTENER: method -> notifyElementExpired(Ehcache arg0, Element arg1)");
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#notifyElementPut(net.sf.ehcache.Ehcache, net.sf.ehcache.Element)
     */
    public void notifyElementPut(Ehcache arg0, Element arg1)
            throws CacheException {
        log.debug("EHCACHE LISTENER: method -> notifyElementPut(Ehcache arg0, Element arg1)");
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#notifyElementRemoved(net.sf.ehcache.Ehcache, net.sf.ehcache.Element)
     */
    public void notifyElementRemoved(Ehcache arg0, Element arg1)
            throws CacheException {
        log.debug("EHCACHE LISTENER: method -> notifyElementRemoved(Ehcache arg0, Element arg1)");
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#notifyElementUpdated(net.sf.ehcache.Ehcache, net.sf.ehcache.Element)
     */
    public void notifyElementUpdated(Ehcache arg0, Element arg1)
            throws CacheException {
        log.debug("EHCACHE LISTENER: method -> notifyElementUpdated(Ehcache arg0, Element arg1)");
    }

    /* (non-Javadoc)
     * @see net.sf.ehcache.event.CacheEventListener#notifyRemoveAll(net.sf.ehcache.Ehcache)
     */
    public void notifyRemoveAll(Ehcache arg0) {
        log.debug("EHCACHE LISTENER: method -> notifyRemoveAll(Ehcache arg0)");
    }

}
