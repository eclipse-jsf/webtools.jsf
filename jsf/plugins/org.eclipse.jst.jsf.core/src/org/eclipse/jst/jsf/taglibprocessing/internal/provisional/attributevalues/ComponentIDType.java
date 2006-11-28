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
package org.eclipse.jst.jsf.taglibprocessing.internal.provisional.attributevalues;


import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidationMessage;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ValidationMessage;

/**
 * Meta-data processing type representing an "id" attribute
 * @author Gerry Kessler - Oracle
 */
public class ComponentIDType extends ValueBindingType implements
		IValidValues, IValidELValues {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.internal.provisional.attributevalues.ValueBindingType#getExpectedRuntimeType()
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		throw new ELIsNotValidException(Messages.ComponentIDType_invalid_as_el);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {
		IValidationMessage msg = null;
		if (value != null && value.trim().length() == 0)
			msg = new ValidationMessage(Messages.ComponentIDType_invalid_value);
		else {
			//we could validate uniqueness, but will not for the time being.  This would require a DT FacesContext.
			//any other coercion rules apply here?
			return true;
		}			
		getValidationMessages().add(msg);
		return false;
	}
}
