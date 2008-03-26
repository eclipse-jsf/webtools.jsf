/*******************************************************************************
 * Copyright (c) 2007 Vadim Dmitriev and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Vadim Dmitriev - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;

/**
 * Abstract meta-data processing type representing an enumeration attribute value
 * of multiple runtime types
 * 
 * @author Vadim Dmitriev
 */
public abstract class MultiSignatureEnumerationType extends AbstractEnumerationType {

	/**
	 * @return return Java types as array of strings (i.e. 
	 * new String[]{"boolean", "java.lang.String"}, etc.)
	 * Must not be null.
	 */
	protected abstract String[] getReturnTypes();
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues#getExpectedRuntimeType()
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException {
		String[] types = getReturnTypes();
		int len = types.length;
		
		String[] signaturedTypes = new String[ len ];
		for( int i = 0; i < len; i++ )
		{
			signaturedTypes[ i ] = Signature.createTypeSignature(types[ i ], true);
		}
		return new CompositeType( signaturedTypes, getAssignmentType());
	}

}
