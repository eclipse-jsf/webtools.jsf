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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * An instance of a java bean
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 * 
 * @author cbateman
 * @model
 */
public interface IBeanInstanceSymbol extends IInstanceSymbol, IDescribedInDetail {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";  //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Properties</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBeanInstanceSymbol_Properties()
     * @model type="org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol" changeable="false" volatile="true"
     * @generated
     */
	EList getProperties();

    /**
     * Returns the value of the '<em><b>Methods</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Methods</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBeanInstanceSymbol_Methods()
     * @model type="org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol" changeable="false" volatile="true"
     * @generated
     */
	EList getMethods();

    /**
     * <!-- begin-user-doc -->
     * This is a convenience method equivalent to to (IJavaTypeDescriptor2) getTypeDescriptor
     * @return the type descriptor cast to a java descriptor
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    IJavaTypeDescriptor2 getJavaTypeDescriptor();

    /**
     * <!-- begin-user-doc -->
     * Convenience method equivalent to setTypeDescriptor((IJavaTypeDescriptor2) typeDesc)s
     * @param newTypeDescriptor 
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    void setJavaTypeDescriptor(IJavaTypeDescriptor2 newTypeDescriptor);

}
