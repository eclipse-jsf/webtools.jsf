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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMethod Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIMethodSymbol()
 * @model
 * @generated
 */
public interface IMethodSymbol extends ISymbol {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String copyright = "Copyright 2006 Oracle";

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Signature</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' attribute.
	 * @see #setSignature(String)
	 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIMethodSymbol_Signature()
	 * @model
	 * @generated
	 */
    String getSignature();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol#getSignature <em>Signature</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' attribute.
	 * @see #getSignature()
	 * @generated
	 */
    void setSignature(String value);

} // IMethodSymbol
