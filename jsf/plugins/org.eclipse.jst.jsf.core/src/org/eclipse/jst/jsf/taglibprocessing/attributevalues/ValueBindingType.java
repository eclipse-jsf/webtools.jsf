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

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;

/**
 * Meta-data processing type representing a Value Bound attribute value runtime type
 * that implements IValidValues
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class ValueBindingType extends ExpressionBindingType implements IValidELValues{
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues#getExpectedRuntimeType()
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		String returnType = getReturnType();
		if (returnType == null)
			return null;
		
		String methodSig = Signature.createTypeSignature(returnType, true);
		return new CompositeType(methodSig, getAssignmentValue());
	}
	
	/**
	 * @return {@link IAssignable}.LHS or {@link IAssignable}.RHS 
	 */
	protected int getAssignmentValue(){
		return (getIsSetValueRequired() ? IAssignable.ASSIGNMENT_TYPE_LHS : 0) | IAssignable.ASSIGNMENT_TYPE_RHS;
		
	}
	
	/**
	 * @return is runtime setter required?
	 */
	protected boolean getIsSetValueRequired(){
		String value = getTraitValueAsString(RUNTIME_SETTER_REQUIRED);
		if (value == null || value.trim().length() == 0)
			return false; 
		else if (value.trim().equals("true")) //$NON-NLS-1$
			return true;
		else
			return false;		
	}
}
