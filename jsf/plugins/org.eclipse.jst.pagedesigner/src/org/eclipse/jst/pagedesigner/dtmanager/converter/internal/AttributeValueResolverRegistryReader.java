/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 * @version 1.5
 */
public class AttributeValueResolverRegistryReader extends
        AbstractSimpleClassExtensionRegistryReader<IAttributeValueResolver>
{
    private final static AttributeValueResolverRegistryReader INSTANCE = new AttributeValueResolverRegistryReader();

    /**
     * Not externally instantiable.
     */
    private AttributeValueResolverRegistryReader()
    {
        super(PDPlugin.getPluginId(),
                IJMTConstants.EXTENSION_POINT_PAGEDESIGNER,
                "attributeValueResolver", //$NON-NLS-1$
                "class", //$NON-NLS-1$
                new CompareOrgEclipseJstContributorsLastComparator<IAttributeValueResolver>()
        );
    }

    /**
     * @return singleton instance.
     */
    public static AttributeValueResolverRegistryReader getInstance()
    {
        return INSTANCE;
    }

    @Override
    protected void handleLoadFailure(CoreException ce)
    {
        PDPlugin.log("Loading Attribute Value Resolver extensions", ce); //$NON-NLS-1$
    }

}
