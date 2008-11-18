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

package org.eclipse.jst.jsf.metadataprocessors.features;

import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

/**
 * Interface providing a default value.
 * 
 * @author Gerry Kessler - Oracle
 * 
 * <p><b>Provisional API - subject to change</b></p>
 *
 */
public interface IDefaultValue extends IMetaDataEnabledFeature {
	/**
	 * Default name of property in annotation file to use when supplying default values from meta-data 
	 */
	String DEFAULT_VALUE_PROP_NAME = "default-value";	 //$NON-NLS-1$
	
	/**
	 * @return default value or null if one not defined
	 */
	public String getDefaultValue();
}
