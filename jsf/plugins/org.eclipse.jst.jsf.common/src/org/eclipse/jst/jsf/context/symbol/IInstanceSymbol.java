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

package org.eclipse.jst.jsf.context.symbol;

/**
 * <!-- begin-user-doc -->
 * A symbol that represents an instance of something, typically
 * a variable.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 * @author cbateman
 * @model
 */
public interface IInstanceSymbol extends IObjectSymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

	/**
	 * @return the type descriptor for this instance
	 * @model
	 */
	ITypeDescriptor	getTypeDescriptor();
    /**
     * Returns the value of the '<em><b>Type Resolved</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Resolved</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type Resolved</em>' attribute.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIInstanceSymbol_TypeResolved()
     * @model changeable="false" volatile="true"
     * @generated
     */
	boolean isTypeResolved();

    /**
     * Returns the value of the '<em><b>Runtime Source</b></em>' attribute.
     * The default value is <code>"TAG_INSTANTIATED_SYMBOL"</code>.
     * The literals are from the enumeration {@link org.eclipse.jst.jsf.context.symbol.ERuntimeSource}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Runtime Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Runtime Source</em>' attribute.
     * @see org.eclipse.jst.jsf.context.symbol.ERuntimeSource
     * @see #setRuntimeSource(ERuntimeSource)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIInstanceSymbol_RuntimeSource()
     * @model default="TAG_INSTANTIATED_SYMBOL"
     * @generated
     */
    ERuntimeSource getRuntimeSource();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IInstanceSymbol#getRuntimeSource <em>Runtime Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Runtime Source</em>' attribute.
     * @see org.eclipse.jst.jsf.context.symbol.ERuntimeSource
     * @see #getRuntimeSource()
     * @generated
     */
    void setRuntimeSource(ERuntimeSource value);

}
