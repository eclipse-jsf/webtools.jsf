/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal;

import static org.eclipse.jst.jsf.core.internal.JSFCorePlugin.PLUGIN_ID;
import static org.eclipse.jst.jsf.core.internal.JSFCorePlugin.TAG_REGISTRY_FACTORY_PROVIDER_ID;

import java.util.Comparator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;

/**
 * Loader class for the tag registry factory provider extension point.
 *
 */
public class TagRegistryFactoryProviderLoader extends
        AbstractSimpleClassExtensionRegistryReader<ITagRegistryFactoryProvider>
{

    /**
     * @param listComparator
     */
    protected TagRegistryFactoryProviderLoader(
            Comparator<org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader.SortableExecutableExtension<ITagRegistryFactoryProvider>> listComparator)
    {
        super(PLUGIN_ID, TAG_REGISTRY_FACTORY_PROVIDER_ID, "factoryProvider", "class", NO_SORT); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    protected void handleLoadFailure(CoreException ce)
    {
        JSFCorePlugin.log(ce, "Loading tag registry factory provider extension points"); //$NON-NLS-1$
    }
}
