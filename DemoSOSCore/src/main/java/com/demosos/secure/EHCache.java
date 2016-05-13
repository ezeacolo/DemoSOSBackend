/**
 * Class EHCache.
 *
 * @author DemoSOS
 */
package com.demosos.secure;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

/**
 * Administra el contenedor de seguridad implementado por una cache.
 *
 * @author DemoSOS
 */
public class EHCache {
    public static final String SECURITY_CONTAINER_NAME = "securityContainer";
    public static CacheManager manager = null;
    private static Logger log = Logger.getLogger(EHCache.class);

    static {
        manager = new CacheManager(EHCache.class.getResourceAsStream("cache.xml"));
    }

    public EHCache() {
        System.out.println("EHCache constructor");
        log.debug("EHCache constructor");
    }

    /**
     * Method: loadUsuario
     * Description: Obtiene el objeto usuario si este se encuentra cacheado. 
     * @param token el token de conexion.
     * @return un objeto usuario.
     */
    public static Object loadUsuario(String token) {
        Object result = null;

        try {
            log.debug("EHCACHE: Loading user of the cache with token -> " + token);
            Cache cacheObject = manager.getCache(SECURITY_CONTAINER_NAME);
            result = cacheObject.get(token).getObjectValue();

            if (result != null) {
                saveUsuario(token, result);
            }
        } catch (Exception e) {
            log.debug("EHCACHE: The token is not in the cache.");
        }

        return result;
    }

    /**
     * Method: saveUsuario
     * Description: Graba el objeto usuario a la cache de usuarios. 
     * @param token un token de conexion.
     * @param usuario el objeto usuario a grabar.
     */
    public static void saveUsuario(String token, Object usuario) {
        log.debug("EHCACHE: Saving user in the cache with token -> " + token);
        Cache cacheObject = manager.getCache(SECURITY_CONTAINER_NAME);
        cacheObject.put(new Element(token, usuario));
    }

    /**
     * Method: removeUsuario
     * Description: Remueve un objeto usuario desde la cache. 
     * @param token un token de conexion.
     * @return true si se pudo remover el usuario, false en caso contrario.
     */
    public static boolean removeUsuario(String token) {
        log.debug("EHCACHE: Removing user in the cache with token -> " + token);
        Cache cacheObject = manager.getCache(SECURITY_CONTAINER_NAME);
        return cacheObject.remove(token);
    }

}
