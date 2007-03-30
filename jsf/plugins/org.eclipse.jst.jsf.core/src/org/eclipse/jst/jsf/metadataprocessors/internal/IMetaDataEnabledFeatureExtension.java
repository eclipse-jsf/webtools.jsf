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

package org.eclipse.jst.jsf.metadataprocessors.internal;

/**
 * Interface used to bind feature extensions to metadata enabled types 
 *
 */
public interface IMetaDataEnabledFeatureExtension {
	/**
	 * @return bundle id
	 */
	public String getBundleID();
	/**
	 * @return type id
	 */
	public String getTypeID();
	/**
	 * @return class name to use for type.   Must be located within the bundle.
	 */
	public String getClassName();
}
