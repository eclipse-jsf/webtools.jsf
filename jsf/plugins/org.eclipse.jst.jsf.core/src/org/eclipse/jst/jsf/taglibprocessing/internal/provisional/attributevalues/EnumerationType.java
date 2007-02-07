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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ValidationMessage;

/**
 * Abstract meta-data processing type representing an enumeration attribute value runtime type
 * @author Gerry Kessler - Oracle
 */
public abstract class EnumerationType extends AbstractRootTypeDescriptor implements IValidValues, IDefaultValue, IValidELValues{
	private List validationMessages;
	protected static List EMPTY_LIST = new ArrayList(0);
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidValues#getValidationMessages()
	 */
	public List getValidationMessages() {
		if (validationMessages == null){
			validationMessages = new ArrayList();
		}
		return validationMessages;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IDefaultValue#getDefaultValue()
	 */
	public String getDefaultValue() {
		return getTraitValueAsString(IDefaultValue.DEFAULT_VALUE_PROP_NAME);		
	}
	
	protected List getMDPossibleValues() {
		return getTraitValueAsListOfStrings(IPossibleValues.POSSIBLE_VALUES_PROP_NAME);
	}
	
	protected List getMDPossibleValuesForDisplay() {
		return getTraitValueAsListOfStrings(IPossibleValues.POSSIBLE_VALUES_FOR_DISPLAY_PROP_NAME);
	}
	
	protected List getMDValidValues() {
		return getTraitValueAsListOfStrings(IValidValues.VALID_VALUES_PROP_NAME);		
	}
	
	protected String getMDValidationMessage() {
		return getTraitValueAsString(IValidValues.VALID_VALUES_MESSAGE_PROP_NAME);			
	}

	protected int getMDValidationSeverity() {
		String val = getTraitValueAsString(IValidValues.VALID_VALUES_SEVERITY_PROP_NAME);		
		if (val == null)
			return IStatus.WARNING;
		
		int severity = Integer.valueOf(val).intValue();
		return severity;
	}

	protected String getMDValidationCode() {
		return getTraitValueAsString(IValidValues.VALID_VALUES_CODE_PROP_NAME);		
	}
	
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
	 * @return return Java type as string (i.e. boolean, java.lang.String, etc.)
	 * Must not be null.
	 */
	protected abstract String getReturnType();
	protected int getAssignmentType(){
		return 0;
	}
	
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		String type = Signature.createTypeSignature(getReturnType(), true);
		return new CompositeType(type, getAssignmentType());
	}
}
