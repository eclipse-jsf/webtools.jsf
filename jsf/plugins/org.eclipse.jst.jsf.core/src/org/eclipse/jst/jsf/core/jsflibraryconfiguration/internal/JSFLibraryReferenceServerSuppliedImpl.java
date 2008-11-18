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

import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferenceServerSupplied;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFVersion;

/**
 * @author gekessle
 * @deprecated
 */
public class JSFLibraryReferenceServerSuppliedImpl 
	extends AbstractJSFLibraryReferenceImpl 
	implements JSFLibraryReferenceServerSupplied {
	
	/**
	 * Library display Label 
	 */
	public final static String SERVER_SUPPLIED = Messages.JSFLibraryReferenceServerSuppliedImpl_Label; 
	/**
	 * Constructor
	 */
	public JSFLibraryReferenceServerSuppliedImpl(){
		//TODO: replace label with constant
		super(JSFLibraryReferenceServerSupplied.ID, SERVER_SUPPLIED, true); 
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer("ServerSupplied: ("); //$NON-NLS-1$
		buf.append(super.toString());
		buf.append(")"); //$NON-NLS-1$
		
		return buf.toString();
	}
	
	protected JSFLibrary getLibrary(){
		return null;
	}
	
	public JSFVersion getMaxSupportedVersion() {
		return JSFVersion.UNKNOWN;
	}
//
}
