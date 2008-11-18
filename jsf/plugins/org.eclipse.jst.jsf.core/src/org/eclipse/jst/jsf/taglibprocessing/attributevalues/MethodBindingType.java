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


import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Meta-data processing type representing an method-binding attribute value runtime type
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 */
public class MethodBindingType extends ExpressionBindingType implements IValidELValues, IValidValues{
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues#getExpectedRuntimeType()
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		String[] params = getParams();
        
        // need to "signaturize" each parameter
        for (int param = 0; param < params.length; param++)
        {
            params[param] = Signature.createTypeSignature(params[param],true);
        }
        final String runtimeType = getReturnType();
        
        String returnTypeSignature = null;
        
        if (runtimeType != null)
        {
             returnTypeSignature = Signature.createTypeSignature(runtimeType, true);
        }
        else
        {
            JSFCorePlugin.log(IStatus.INFO, "Missing metadata for trait "+RUNTIME_RETURN_TYPE+" for entity "+getMetaDataContext().getEntity()); //$NON-NLS-1$ //$NON-NLS-2$
        }

		if (returnTypeSignature == null)
        {
		    return null;
        }
		
		String methodSig = Signature.createMethodSignature(params, returnTypeSignature);
		return new CompositeType(methodSig, IAssignable.ASSIGNMENT_TYPE_NONE);
	}

	/**
	 * Non-EL values are invalid for method bound attribute values
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {		
		//if this is being called, we are being called in an non-EL context which is invalid.
		IValidationMessage msg = new ValidationMessage(Messages.MethodBindingType_invalid_value);
		getValidationMessages().add(msg);
		return false;
	}

	
}
