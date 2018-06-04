package edu.wustl.mir.ctt.util;

/**
 *
 * @author drm
 */
public class Util {
    
    // this is cut and paste from jsf mojarra src.
    // jsfsrc/com/sun/faces/util/Util.java
    public static ClassLoader getCurrentLoader(Object fallbackClass) {
        ClassLoader loader = getContextClassLoader();
        if (loader == null) {
            loader = fallbackClass.getClass().getClassLoader();
        }
        return loader;
    }

    // this is cut and paste from jsf mojarra src.
    // jsfsrc/com/sun/faces/util/Util.java
    private static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        } else {
            return (ClassLoader)
                java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction() {
                        public Object run() {
                            return Thread.currentThread().getContextClassLoader();
                        }
                    });
        }
    }

    
}
