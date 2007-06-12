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

import java.util.List;

import org.eclipse.osgi.util.NLS;

/**
 * Meta-data processing type representing a "double" attribute value runtime type
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class DoubleType extends NumberType {
	private static final String INVALID_DOUBLE = Messages.DoubleType_invalid_double;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.EnumerationType#getReturnType()
	 */
	protected String getReturnType(){ return "double";} //$NON-NLS-1$
	
	/**
	 * Type coercion according to JSP 2.0 spec: JSP.1.14.2.1 Conversions from String values
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 **/
	public boolean isValidValue(String value) {		
		try {
			Double dbl = Double.valueOf(value);
			exceedsMaxValue(dbl.doubleValue());
			lessThanMinValue(dbl.doubleValue());
			if (!(minFound || maxFound)){
				List validVals = getMDValidValues();
				if (!validVals.isEmpty()){
					if (!validVals.contains(value)){
						addNewValidationMessage(Messages.DoubleType_invalid_member);//fix me
					}
				}
			}
			return getValidationMessages().isEmpty();
		} catch (NumberFormatException e) {
			addNewValidationMessage(INVALID_DOUBLE);
			return false;
		}

	}

	private void exceedsMaxValue(double dbl) {
		String strMax = getValidMaximumValue();
		if (strMax != null){
			try {
				double max = Double.valueOf(strMax).doubleValue();
				maxFound = true;
				if (dbl > max){
					addNewValidationMessage(NLS.bind(EXCEEDS_MAX, strMax));
				}
			} catch (NumberFormatException e) {
				//TODO: ignore error????  or log it????
			}
		}
		
	}
	
	private void lessThanMinValue(double dbl) {
		String strMin = getValidMinimumValue();
		if (strMin != null){
			try {
				double max = Double.valueOf(strMin).doubleValue();
				minFound = true;
				if (dbl < max){
					addNewValidationMessage(NLS.bind(LESS_THAN_MIN, strMin));
				}
			} catch (NumberFormatException e) {
				//TODO: ignore error????  or log it????
			}
		}
		
	}

}
