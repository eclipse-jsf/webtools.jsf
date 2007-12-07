/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;


import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Meta-data processing type representing an "id" attribute
 * Represents an ID or NAME HTML attribute type.
 * <br>
 * Attribute values of type ID and NAME must begin with a letter in the range A-Z or a-z or underscore ("_") and 
 * may be followed by letters (A-Z, a-z), digits (0-9), hyphens ("-"), underscores ("_"), colons (":"),
 * and periods ("."). 
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 * TODO: finish regex validation
 */
public class ComponentIDType extends ValueBindingType implements
		IValidValues, IValidELValues {

//	private static Pattern idPattern1 = Pattern.compile("[A-Za-z_]");;
//	private static Pattern idPattern2 = Pattern.compile("^[:\\w\\.\\-]*");
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.ValueBindingType#getExpectedRuntimeType()
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		throw new ELIsNotValidException(Messages.ComponentIDType_invalid_as_el);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
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

//		if (value != null && value.trim().length() == 0)
//			msg = new ValidationMessage(Messages.ComponentIDType_invalid_value);
//
////		else if (!idPattern.matcher(value.trim()).find())
////			fail = true;
//		else if (!idPattern1.matcher(value.trim().substring(0, 1)).find())
//			msg = new ValidationMessage("id must begin with alpha character");
//		else if ((value.trim().length() > 1) && (!( idPattern2.matcher(value.trim().substring(1)).find())))
//			msg = new ValidationMessage("illegal characters in id: must be alphanum, period, colon, and hyphen only");
//		else {
//			//we could validate uniqueness, but will not for the time being.  This would require a DT FacesContext.
//			//any other coercion rules apply here?		
//		}
//		if (msg != null)
//			getValidationMessages().add(msg);
//			
//		return getValidationMessages().size() == 0;
	}
}
