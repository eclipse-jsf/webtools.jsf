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
package org.eclipse.jst.jsf.facelet.core.internal.facet;

import org.eclipse.wst.common.project.facet.core.IDefaultVersionProvider;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Provides the default Facelet project facet version. Currently always returns
 * "1.1" facet version.
 */
public final class FaceletFacetDefaultVersionProvider implements
        IDefaultVersionProvider
{

    /**
     * The global id for the Facelet facet
     */
    private static final String DEFAULT_FACET_ID      = FaceletFacet.FACET_ID;

    private static final String DEFAULT_FACET_VERSION = "1.0"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.common.project.facet.core.IDefaultVersionProvider#getDefaultVersion()
     */

    public IProjectFacetVersion getDefaultVersion()
    {
        return ProjectFacetsManager.getProjectFacet(DEFAULT_FACET_ID)
                .getVersion(DEFAULT_FACET_VERSION);
    }

}
