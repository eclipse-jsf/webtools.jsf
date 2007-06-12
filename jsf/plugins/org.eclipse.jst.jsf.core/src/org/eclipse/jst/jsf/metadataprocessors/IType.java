/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
