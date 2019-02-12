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
package org.eclipse.jst.jsf.core.jsflibraryconfiguration;

import java.util.Collection;

import org.eclipse.jdt.core.IClasspathEntry;

/**
 * Represents a reference to a JSF Library on a project
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @deprecated
 */
public abstract interface JSFLibraryReference {
	/**
	 * @return id for the library
	 */
	public String getId();	
	/**
	 * @return name
	 */
	public String getName();
	/**
	 * @return collection of jars as {@link IClasspathEntry}s
	 */
	public Collection<IClasspathEntry> getJars();
	/**
	 * @return {@link JSFVersion} value
	 */
	public JSFVersion getMaxSupportedVersion();
	/**
	 * @return label user sees for this library
	 */
	public String getLabel();
	/**
	 * @return flag 
	 */
	public boolean isJSFImplementation();
	/**
	 * @return is deployed (marked as J2EE Module Dependency)
	 */
	public boolean isDeployed();	
}
