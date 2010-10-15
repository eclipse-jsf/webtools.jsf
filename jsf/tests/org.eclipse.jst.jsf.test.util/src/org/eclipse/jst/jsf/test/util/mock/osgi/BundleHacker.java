package org.eclipse.jst.jsf.test.util.mock.osgi;

import java.lang.reflect.Field;

import org.eclipse.osgi.framework.adaptor.FrameworkAdaptor;
import org.eclipse.osgi.framework.internal.core.BundleContextImpl;
import org.eclipse.osgi.framework.internal.core.BundleHost;
import org.eclipse.osgi.framework.internal.core.Framework;
import org.osgi.framework.BundleException;

/**
 * Utility methods for hacking OSGI bundle dependencies.
 * 
 * @author cbateman
 * 
 */
public class BundleHacker
{

    public void mockBundleContext(Class<?> clazz) throws NoSuchFieldException,
            BundleException, IllegalAccessException
    {
        final Field declaredField = clazz.getDeclaredField("context");
        declaredField.setAccessible(true);

        final FrameworkAdaptor adaptor = new MockFrameworkAdaptor();
        final Framework framework = new Framework(adaptor);
        final BundleHost host = new BundleHost(null, framework);
        declaredField.set(null, new BundleContextImpl(host)
        {
        });
    }
}
