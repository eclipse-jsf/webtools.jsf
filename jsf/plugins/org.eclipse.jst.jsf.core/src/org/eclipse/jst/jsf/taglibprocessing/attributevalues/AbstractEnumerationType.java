/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *	  Vadim Dmitriev
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Abstract meta-data processing type representing an enumeration attribute value runtime type
 * <p><b>Provisional API - subject to change</b></p>
 */
public abstract class AbstractEnumerationType extends AbstractRootTypeDescriptor implements IValidValues, IDefaultValue, IValidELValues{
	private List validationMessages;
	
	/**
	 * An empty list
	 */
	protected static List EMPTY_LIST = new ArrayList(0);
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#getValidationMessages()
	 */
	public List getValidationMessages() {
		if (validationMessages == null){
			validationMessages = new ArrayList();
		}
		return validationMessages;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue#getDefaultValue()
	 */
	public String getDefaultValue() {
		return getTraitValueAsString(IDefaultValue.DEFAULT_VALUE_PROP_NAME);		
	}
	
	/**
	 * @return possible values of type as list of Strings using 
	 * {@link IPossibleValues}.POSSIBLE_VALUES_PROP_NAME trait name
	 */
	protected List getMDPossibleValues() {
		return getTraitValueAsListOfStrings(IPossibleValues.POSSIBLE_VALUES_PROP_NAME);
	}
	
	/**
	 * @return possible values of type to dislay as list of Strings using 
	 * {@link IPossibleValues}.POSSIBLE_VALUES_FOR_DISPLAY_PROP_NAME trait name
	 */
	protected List getMDPossibleValuesForDisplay() {
		return getTraitValueAsListOfStrings(IPossibleValues.POSSIBLE_VALUES_FOR_DISPLAY_PROP_NAME);
	}
	
	/**
	 * @return  list of valid values as list of Strings using 
	 * {@link IValidValues}.VALID_VALUES_PROP_NAME trait name
	 */
	protected List getMDValidValues() {
		return getTraitValueAsListOfStrings(IValidValues.VALID_VALUES_PROP_NAME);		
	}
	
	/**
	 * @return Validation message for type using
	 * {@link IValidValues}.VALID_VALUES_MESSAGE_PROP_NAME trait name
	 */
	protected String getMDValidationMessage() {
		return getTraitValueAsString(IValidValues.VALID_VALUES_MESSAGE_PROP_NAME);			
	}

	/**
	 * @return Validation severity int value for type using
	 * {@link IValidValues}.VALID_VALUES_SEVERITY_PROP_NAME trait name
	 */
	protected int getMDValidationSeverity() {
		String val = getTraitValueAsString(IValidValues.VALID_VALUES_SEVERITY_PROP_NAME);		
		if (val == null)
			return IStatus.WARNING;
		
		int severity = Integer.valueOf(val).intValue();
		return severity;
	}

	/**
	 * @return Validation code as String value for type using
	 * {@link IValidValues}.VALID_VALUES_CODE_PROP_NAME trait name
	 */
	protected String getMDValidationCode() {
		return getTraitValueAsString(IValidValues.VALID_VALUES_CODE_PROP_NAME);		
	}
	
	/**
	 * Create a {@link ValidationMessage} from metadata or use default message
	 * and add it to the collection of validation messages
	 * @param defaultMsg
	 */
	protected void addNewValidationMessage(String defaultMsg) {
		String msg = getMDValidationMessage();
		if (msg == null || msg.equals("")) //$NON-NLS-1$
			msg = defaultMsg;
		
		String code = getMDValidationCode();
		int severity = getMDValidationSeverity();
		ValidationMessage val = new ValidationMessage(msg, code, severity);
		getValidationMessages().add(val);
	}
	
	/**
	 * @return the value of {@link org.eclipse.jst.jsf.common.internal.types.IAssignable}.ASSIGNMENT_TYPE_NONE.  Subclasses to override.
	 */
	protected int getAssignmentType(){
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues#getExpectedRuntimeType()
	 */
	public  abstract CompositeType getExpectedRuntimeType() throws ELIsNotValidException;
}
