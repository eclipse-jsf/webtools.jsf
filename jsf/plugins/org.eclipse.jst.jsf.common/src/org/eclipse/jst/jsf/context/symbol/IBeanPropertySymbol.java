/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol;

/**
 * 
 * <!-- begin-user-doc -->
 * A property of a bean
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 * 
 * @author cbateman
 * @model
 */
public interface IBeanPropertySymbol extends IPropertySymbol, IDescribedInDetail {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

	/**
	 * @return the bean that owns this property
	 * @model
	 */
	IJavaTypeDescriptor2		getOwner();
	
    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol#getOwner <em>Owner</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Owner</em>' reference.
     * @see #getOwner()
     * @generated
     */
	void setOwner(IJavaTypeDescriptor2 value);

	/**
	 * @return true if the bean has getter for this property
	 * @model
	 */
	boolean					isReadable();
	
	/**
	 * @return true if the bean has a setter fot this property
	 * @model
	 */
	boolean					isWritable();
}
