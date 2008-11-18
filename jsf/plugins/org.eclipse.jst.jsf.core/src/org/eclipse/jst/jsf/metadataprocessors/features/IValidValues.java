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
 * An {@link IMetaDataEnabledFeature} for validating values using metadata
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IValidValues extends IMetaDataEnabledFeature{
	/**
	 * Trait name for valid-values
	 */
	public static final String VALID_VALUES_PROP_NAME = "valid-values"; //$NON-NLS-1$
	
	/**
	 * Trait name for valid-values-code
	 */
	public static final String VALID_VALUES_CODE_PROP_NAME = VALID_VALUES_PROP_NAME + "-code"; //$NON-NLS-1$
	/**
	 * Trait name for valid-values-severity
	 */
	public static final String VALID_VALUES_SEVERITY_PROP_NAME = VALID_VALUES_PROP_NAME + "-severity"; //$NON-NLS-1$
	/**
	 * Trait name for valid-values-message
	 */
	public static final String VALID_VALUES_MESSAGE_PROP_NAME = VALID_VALUES_PROP_NAME + "-message"; //$NON-NLS-1$
	/**
	 * Trait name for valid-maximum
	 */
	public static final String VALID_VALUES_MAX_PROP_NAME = "valid-maximum"; //$NON-NLS-1$
	/**
	 * Trait name for valid-minimum
	 */
	public static final String VALID_VALUES_MIN_PROP_NAME = "valid-minimum"; //$NON-NLS-1$
	/**
	 * @param value fully resolved value as String
	 * @return true if is valid
	 */
	public boolean isValidValue(String value);
	/**
	 * @return List of IValidationMessage objects if invalid
	 * Must return empty list rather than null
	 * Call to isValidValue(String value) required before this should be called.
	 */
	public List getValidationMessages();
}
