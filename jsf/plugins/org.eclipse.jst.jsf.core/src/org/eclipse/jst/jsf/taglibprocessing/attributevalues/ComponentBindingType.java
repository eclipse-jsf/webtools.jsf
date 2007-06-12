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


import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Meta-data processing type representing a "binding" attribute
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class ComponentBindingType extends ValueBindingType implements
		IValidValues, IValidELValues {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {
		//Strings are invalid.   Requires a EL value binding.
		IValidationMessage msg = new ValidationMessage(Messages.ComponentBindingType_invalid_value);
		getValidationMessages().add(msg);
		return false;
	}
}
