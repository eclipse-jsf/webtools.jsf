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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * Meta-data processing type representing a boolean attribute value runtime type
 * that implements IPossibleValues, IValidValues, IDefaultValue, IValidELValues
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class BooleanType extends EnumerationType implements IValidValues, IPossibleValues, IDefaultValue{
	private static final String TRUE_VAL = "true"; //$NON-NLS-1$
	private static final String FALSE_VAL = "false"; //$NON-NLS-1$
	
	private static final String BOOLTYPE_VALIDATION_MSG = Messages.BooleanType_invalid_values;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.EnumerationType#getReturnType()
	 */
	protected String getReturnType(){ return "boolean";} //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues#getPossibleValues()
	 */
	public List getPossibleValues() {
		String def = getDefaultValue();
		List vals = new ArrayList();
		vals.add(new PossibleValue(TRUE_VAL, TRUE_VAL, def != null && def.equals(TRUE_VAL) ));
		vals.add(new PossibleValue(FALSE_VAL, FALSE_VAL, def != null && def.equals(FALSE_VAL) ));
		return vals;
	}
	
	/**
	 * Type coercion according to JSP 2.0 spec: JSP.1.14.2.1 Conversions from String values
	 * Although not completely faithful to spec, we will validate values as either 'true' or 'false'
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 **/
	public boolean isValidValue(String value) {		
		if(TRUE_VAL.equalsIgnoreCase(value) || FALSE_VAL.equalsIgnoreCase(value)) {
			return true;
		}
		addNewValidationMessage(BOOLTYPE_VALIDATION_MSG);	
		return false;
	}

}
