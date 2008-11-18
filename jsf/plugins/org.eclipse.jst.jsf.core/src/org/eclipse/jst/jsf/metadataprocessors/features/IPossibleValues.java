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

import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

/**
 * Interface for providing possible values.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 *
 */
public interface IPossibleValues extends IMetaDataEnabledFeature{
	
	/**
	 * Default name of property in annotation file to use when supplying possible values from meta-data 
	 */
	public static final String POSSIBLE_VALUES_PROP_NAME = "valid-values";	 //$NON-NLS-1$
	/**
	 * Default name of property in annotation file to use when supplying possible displayed values from meta-data 
	 */
	public static final String POSSIBLE_VALUES_FOR_DISPLAY_PROP_NAME = "displayed-values";	 //$NON-NLS-1$
	/**
	 * Default name of property in annotation file to use when supplying icon displayed values from meta-data 
	 */
	public static final String POSSIBLE_VALUES_SMALL_ICON_PROP_NAME = "small-icon";	 //$NON-NLS-1$
	/**
	 * @return List of IPossibleValue instances representing possible values
	 * Implementer must ensure that an empty rather than null list is returned if no values are posssible.
	 */
	public List getPossibleValues();

}
