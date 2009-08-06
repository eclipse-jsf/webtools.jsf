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

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.wst.common.project.facet.core.IDefaultVersionProvider;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Provides the default JSF project facet version.
 * Currently always returns "1.1" facet version.   Eventually should be computed from the project's facet context.
 */
public final class JSFFacetDefaultVersionProvider implements IDefaultVersionProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.project.facet.core.IDefaultVersionProvider#getDefaultVersion()
	 */
	public IProjectFacetVersion getDefaultVersion() {		
		return ProjectFacetsManager.getProjectFacet(IJSFCoreConstants.JSF_CORE_FACET_ID).getVersion(IJSFCoreConstants.FACET_VERSION_1_2);
	}

}
