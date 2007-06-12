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
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

/**
 * Abstract meta-data processing type representing a numeric attribute value runtime type
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public abstract class NumberType extends EnumerationType{
	/**
	 * flag indicating max value metadata was found
	 */
	protected boolean maxFound = false;
	/**
	 *  flag indicating min value metadata was found
	 */
	protected boolean minFound = false;
	
	/**
	 * Validation message when value has exceeded maximum
	 */
	protected String EXCEEDS_MAX = Messages.NumberType_max_val;
	/**
	 * Validation message when value is less than minimum
	 */
	protected String LESS_THAN_MIN = Messages.NumberType_min_val;
	
	/**
	 * @return maximum value from property named IValidValues.VALID_VALUES_MAX_PROP_NAME
	 */
	protected String getValidMaximumValue(){
		return getTraitValueAsString(IValidValues.VALID_VALUES_MAX_PROP_NAME);
	}
		
	/**
	 * @return minimum value from property named IValidValues.VALID_VALUES_MIN_PROP_NAME
	 */
	protected String getValidMinimumValue(){
		return getTraitValueAsString(IValidValues.VALID_VALUES_MIN_PROP_NAME);
	}
}
