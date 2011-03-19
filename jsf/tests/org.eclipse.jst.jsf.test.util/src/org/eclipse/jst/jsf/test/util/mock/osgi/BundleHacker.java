/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util.mock.osgi;

import java.lang.reflect.Field;

import org.eclipse.osgi.framework.adaptor.FrameworkAdaptor;
import org.eclipse.osgi.framework.internal.core.BundleContextImpl;
import org.eclipse.osgi.framework.internal.core.BundleHost;
import org.eclipse.osgi.framework.internal.core.Framework;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Utility methods for hacking OSGI bundle dependencies.
 * 
 * @author cbateman
 * 
 */
public class BundleHacker
{
    /**
     * @param clazz
     * @param instance -- the instance to set the mock bundle context or null if the
     * field is static on clazz.
     * @return 
     * @throws NoSuchFieldException
     * @throws BundleException
     * @throws IllegalAccessException
     */
    public BundleContext mockBundleContext(Class<?> clazz, Object instance) throws NoSuchFieldException,
            BundleException, IllegalAccessException
    {
        final Field declaredField = clazz.getDeclaredField("context");
        declaredField.setAccessible(true);

        final BundleHost host = createMockBundleHost();
        BundleContextImpl bundleContextImpl = new BundleContextImpl(host)
        {
        };
        declaredField.set(instance, bundleContextImpl);
        return bundleContextImpl;
    }

    private BundleHost createMockBundleHost() throws BundleException
    {
        final FrameworkAdaptor adaptor = new MockFrameworkAdaptor();
        final Framework framework = new Framework(adaptor);
        final BundleHost host = new BundleHost(null, framework);
        return host;
    }
}
