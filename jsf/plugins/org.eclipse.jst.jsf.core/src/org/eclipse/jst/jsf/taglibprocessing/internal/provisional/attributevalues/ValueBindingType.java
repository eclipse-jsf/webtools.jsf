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

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidELValues;

/**
 * Meta-data processing type representing a Value Bound attribute value runtime type
 * that implements IValidValues
 * 
 * @author Gerry Kessler - Oracle
 */
public class ValueBindingType extends ExpressionBindingType implements IValidELValues{
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		String returnType = getReturnType();
		if (returnType == null)
			return null;
		
		String methodSig = Signature.createTypeSignature(returnType, true);
		return new CompositeType(methodSig, getAssignmentValue());
	}
	
	protected int getAssignmentValue(){
		return (getIsSetValueRequired() ? IAssignable.ASSIGNMENT_TYPE_LHS : 0) | IAssignable.ASSIGNMENT_TYPE_RHS;
		
	}
	
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
