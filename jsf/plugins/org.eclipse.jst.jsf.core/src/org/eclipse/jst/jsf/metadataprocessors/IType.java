/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors;


/**
 * Interface representing the information contained by the type registries.
 * Encapsulates the IConfigurationElement information.
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 *
 */
public interface IType {
	/**
	 * @return type id
	 */
	public String getTypeID();	
	/**
	 * @return bundle id where it is defined
	 */
	public String getBundleID();
	/**
	 * @return classname for the type that must be located within the bundle
	 */
	public String getClassName();
}
