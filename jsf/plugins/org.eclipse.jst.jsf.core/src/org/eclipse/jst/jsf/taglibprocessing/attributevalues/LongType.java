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
 * Meta-data processing type representing a long attribute value runtime type
 * that implements IValidValues, IDefaultValue, IValidELValues
 *  
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class LongType extends NumberType {
	private static final String INVALID_LONG = Messages.LongType_invalid_long;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.EnumerationType#getReturnType()
	 */
	protected String getReturnType(){ return "long";} //$NON-NLS-1$

	/**
	 * Type coercion according to JSP 2.0 spec: JSP.1.14.2.1 Conversions from String values
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 **/
	public boolean isValidValue(String value) {			
		try {
			Long aLong = Long.valueOf(value);
			exceedsMaxValue(aLong.longValue());
			lessThanMinValue(aLong.longValue());
			if (!(minFound || maxFound)){
				List validVals = getMDValidValues();
				if (!validVals.isEmpty()){
					if (!validVals.contains(value)){
						addNewValidationMessage(Messages.LongType_invalid_member);
					}
				}
			}
			return getValidationMessages().isEmpty();
		} catch (NumberFormatException e) {
			addNewValidationMessage(INVALID_LONG);
			return false;
		}

	}

	private void exceedsMaxValue(long aLong) {
		String strMax = getValidMaximumValue();
		if (strMax != null){
			try {
				long max = Long.valueOf(strMax).longValue();
				maxFound = true;
				if (aLong > max){
					addNewValidationMessage(NLS.bind(EXCEEDS_MAX, strMax));
				}
			} catch (NumberFormatException e) {
				//TODO: ignore error????  or log it????
			}
		}
		
	}
	
	private void lessThanMinValue(long aLong) {
		String strMin = getValidMinimumValue();
		if (strMin != null){
			try {
				long min = Long.valueOf(strMin).longValue();
				minFound = true;
				if (aLong < min){
					addNewValidationMessage(NLS.bind(LESS_THAN_MIN, strMin));
				}
			} catch (NumberFormatException e) {
				//TODO: ignore error????  or log it????
			}
		}
		
	}

}
