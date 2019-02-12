/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.ui.internal.project.facet;

import org.eclipse.jst.common.project.facet.ui.libprov.FacetLibraryPropertyPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * TODO
 *
 */
@SuppressWarnings("deprecation")
public final class JSFLibraryPropertyPage

    extends FacetLibraryPropertyPage
    
{
    @Override
    public IProjectFacetVersion getProjectFacetVersion()
    {
        final IProjectFacet jsfFacet = ProjectFacetsManager.getProjectFacet( "jst.jsf" ); //$NON-NLS-1$
        final IFacetedProject fproj = getFacetedProject();
        return fproj.getInstalledVersion( jsfFacet );
    }
    
}
