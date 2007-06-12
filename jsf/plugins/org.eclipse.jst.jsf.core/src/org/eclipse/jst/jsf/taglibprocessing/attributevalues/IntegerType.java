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
 * Meta-data processing type representing an integer attribute value runtime type
 * that implements IValidValues, IDefaultValue, IValidELValues
 * 
 * <p><b>Provisional API - subject to change</b></p> 
 * @author Gerry Kessler - Oracle
 */
public class IntegerType extends NumberType {
	private static final String INVALID_INTEGER = Messages.IntegerType_invalid_integer;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.EnumerationType#getReturnType()
	 */
	protected String getReturnType(){ return "int";} //$NON-NLS-1$
	
	/**
	 * Type coercion according to JSP 2.0 spec: JSP.1.14.2.1 Conversions from String values
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 **/
	public boolean isValidValue(String value) {		
		try {
			Integer anInt = Integer.valueOf(value);
			exceedsMaxValue(anInt.intValue());
			lessThanMinValue(anInt.intValue());
			if (!(minFound || maxFound)){
				List validVals = getMDValidValues();
				if (!validVals.isEmpty()){
					if (!validVals.contains(value)){
						addNewValidationMessage(Messages.IntegerType_invalid_member);//fix me
					}
				}
			}
			return getValidationMessages().isEmpty();
		} catch (NumberFormatException e) {
			addNewValidationMessage(INVALID_INTEGER);
			return false;
		}

	}

	private void exceedsMaxValue(int anInt) {
		String strMax = getValidMaximumValue();
		if (strMax != null){
			try {
				int max = Integer.valueOf(strMax).intValue();
				maxFound = true;
				if (anInt > max){
					addNewValidationMessage(NLS.bind(EXCEEDS_MAX, strMax));
				}
			} catch (NumberFormatException e) {
				//TODO: ignore error????  or log it????
			}
		}
		
	}
	
	private void lessThanMinValue(int anInt) {
		String strMin = getValidMinimumValue();
		if (strMin != null){
			try {
				int max = Integer.valueOf(strMin).intValue();
				minFound = true;
				if (anInt < max){
					addNewValidationMessage(NLS.bind(LESS_THAN_MIN, strMin));
				}
			} catch (NumberFormatException e) {
				//TODO: ignore error????  or log it????
			}
		}
		
	}

}
