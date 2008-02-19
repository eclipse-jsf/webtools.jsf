/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferenceUserDefined;

/**
 * Implementation of a reference to a user defined JSF Library  
 *
 */
public class JSFLibraryReferenceUserDefinedImpl extends AbstractJSFLibraryReferenceImpl implements JSFLibraryReferenceUserDefined{
	/**
	 * @param libRef of type {@link JSFLibraryInternalReference}
	 * @param isDeployed
	 */
	public JSFLibraryReferenceUserDefinedImpl(
			JSFLibraryInternalReference libRef, boolean isDeployed) {

		super(libRef, isDeployed);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal.AbstractJSFLibraryReferenceImpl#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer("UserDefined: (");
		buf.append(super.toString());
		buf.append(")");
		
		return buf.toString();
	}
}
