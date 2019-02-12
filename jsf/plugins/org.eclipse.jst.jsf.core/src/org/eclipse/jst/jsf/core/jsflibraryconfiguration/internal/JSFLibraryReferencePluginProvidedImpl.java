/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferencePluginProvided;

/**
 * @deprecated
 */
public class JSFLibraryReferencePluginProvidedImpl extends AbstractJSFLibraryReferenceImpl implements JSFLibraryReferencePluginProvided {

	/**
	 * Constructor
	 * @param libRef
	 * @param isDeployed 
	 */
	public JSFLibraryReferencePluginProvidedImpl(JSFLibraryInternalReference libRef, boolean isDeployed) {
		super(libRef, isDeployed);
	}

	public String getPluginId() {
		if (getLibrary() != null)
			return ((PluginProvidedJSFLibrary) getLibrary()).getPluginID();
		
		return null;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("PluginProvided: ("); //$NON-NLS-1$
		buf.append(super.toString());
		
		return buf.toString();
	}
}
