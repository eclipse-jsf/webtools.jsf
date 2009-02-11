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
 * A reference to a plugin-defined JSF Library
 *
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @deprecated
 */
public interface JSFLibraryReferencePluginProvided extends JSFLibraryReferenceUserSpecified, JSFLibraryReferenceUserDefined {
	/**
	 * @return plugin id.  May return null if plugin id cannot be determined.  
	 */
	public String getPluginId();
}
