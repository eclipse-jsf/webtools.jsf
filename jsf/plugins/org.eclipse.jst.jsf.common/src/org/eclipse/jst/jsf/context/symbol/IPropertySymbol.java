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
 * A representation of the model object '<em><b>IProperty Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IPropertySymbol#isIntermediate <em>Intermediate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIPropertySymbol()
 * @model
 * @generated
 */
public interface IPropertySymbol extends IObjectSymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Intermediate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Intermediate</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Intermediate</em>' attribute.
     * @see #setIntermediate(boolean)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIPropertySymbol_Intermediate()
     * @model
     * @generated
     */
    boolean isIntermediate();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IPropertySymbol#isIntermediate <em>Intermediate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Intermediate</em>' attribute.
     * @see #isIntermediate()
     * @generated
     */
    void setIntermediate(boolean value);

} // IPropertySymbol
