/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *	  Vadim Dmitriev
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;

/**
 * Abstract meta-data processing type representing an enumeration attribute value runtime type
 * with a single signature
 * 
 * @author Gerry Kessler - Oracle
 */
public abstract class EnumerationType extends AbstractEnumerationType
{

	/**
	 * @return return Java type as string (i.e. boolean, java.lang.String, etc.)
	 * Must not be null.
	 */
	protected abstract String getReturnType();
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues#getExpectedRuntimeType()
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException
	{
		String type = Signature.createTypeSignature(getReturnType(), true);
		return new CompositeType(type, getAssignmentType());
	}
}
