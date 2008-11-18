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
package org.eclipse.jst.jsf.core.jsflibraryconfiguration;

/**
 * A reference to a JSF Library where the implementation library is presumed to be coming from the 
 * server
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @deprecated
 */
public interface JSFLibraryReferenceServerSupplied extends
		JSFLibraryReference {
	
	/**
	 * Constant used for server supplied virtual JSF Library referencew
	 */
	public static final String ID = "_ServerSupplied_"; //$NON-NLS-1$

}
