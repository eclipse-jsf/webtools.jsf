/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectListener;
import org.eclipse.wst.common.project.facet.core.events.IPrimaryRuntimeChangedEvent;

/**
 * Handles primary runtime changed events when the JSF Facet is installed
 * 
 * @since JSF 1.0.1
 */
@SuppressWarnings("deprecation")
public class JSFFacetPrimaryRuntimeChangedListener implements IFacetedProjectListener {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.project.facet.core.events.IFacetedProjectListener#handleEvent(org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent)
	 */
	public void handleEvent(IFacetedProjectEvent event) {
		if (event instanceof IPrimaryRuntimeChangedEvent &&
				getJSFFacetedVersion(event.getProject().getProject()) != null && //must be a JSF faceted project
				JSFLibraryConfigurationHelper.isConfiguredForSystemSuppliedImplementation(event.getProject().getProject())){
			
			try {				
				IProject project = event.getProject().getProject();
				IProjectFacetVersion fv = getJSFFacetedVersion(project);
				ClasspathHelper.removeClasspathEntries(project, fv);
				ClasspathHelper.addClasspathEntries(project, fv);
			} catch (CoreException e) {
				JSFCorePlugin.log(IStatus.ERROR, "Unable to replace server supplied implementation when runtime changed.", e);//$NON-NLS-1$
			}
		}
		
	}

	/**
	 * @param project
	 * @return IProjectFacetVersion and null if not JSF faceted
	 */
	private IProjectFacetVersion getJSFFacetedVersion(IProject project) {
		return JSFAppConfigUtils.getProjectFacet(project);		
	}

}
