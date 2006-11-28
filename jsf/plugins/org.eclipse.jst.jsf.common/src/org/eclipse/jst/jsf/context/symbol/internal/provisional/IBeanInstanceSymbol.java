/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol.internal.provisional;

import org.eclipse.emf.common.util.EList;

/**
 * An instance of a java bean
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
    String copyright = "Copyright 2006 Oracle";

    /**
     * Returns the value of the '<em><b>Properties</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBeanInstanceSymbol_Properties()
     * @model type="org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol" changeable="false" volatile="true"
     * @generated
     */
	EList getProperties();

    /**
     * Returns the value of the '<em><b>Methods</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Methods</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBeanInstanceSymbol_Methods()
     * @model type="org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol" changeable="false" volatile="true"
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
