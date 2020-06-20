/*******************************************************************************
 * Copyright (c) 2010, 2020 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util.mock.osgi;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.EnumSet;

import org.eclipse.osgi.container.Module.Settings;
import org.eclipse.osgi.internal.framework.BundleContextImpl;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.eclipse.osgi.internal.framework.EquinoxContainer;
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

        @SuppressWarnings("unchecked")
		EquinoxContainer container = new EquinoxContainer(Collections.EMPTY_MAP, null);
        EquinoxBundle bundle = createEquinoxBundle(container);

        BundleContextImpl bundleContextImpl = new BundleContextImpl(bundle,container);
//        {
//        };
        declaredField.set(instance, bundleContextImpl);
        return bundleContextImpl;
    }

    private EquinoxBundle createEquinoxBundle(EquinoxContainer equinoxContainer) {
		return new EquinoxBundle(-1L, null, null, EnumSet.of(Settings.USE_ACTIVATION_POLICY), 3, equinoxContainer);
	}

//    private BundleHost createMockBundleHost() throws BundleException
//    {
//        final FrameworkAdaptor adaptor = new MockFrameworkAdaptor();
//        final Framework framework = new Framework(adaptor);
//        final BundleHost host = new BundleHost(null, framework);
//        return host;
//    }
}
