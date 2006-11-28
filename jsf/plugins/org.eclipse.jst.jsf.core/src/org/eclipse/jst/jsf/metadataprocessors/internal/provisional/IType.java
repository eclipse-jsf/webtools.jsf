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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;


/**
 * Interface representing the information contained by the type registries.
 * Encapsulates the IConfigurationElement information.
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public interface IType {
	public String getTypeID();	
	public String getBundleID();
	public String getClassName();
}
