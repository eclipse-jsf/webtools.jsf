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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * A generic descriptor for symbol information
 * 
 * @author cbateman
 * @model
 */
public interface ITypeDescriptor extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

    /**
     * Returns the value of the '<em><b>Properties</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.IPropertySymbol}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getITypeDescriptor_Properties()
     * @model type="org.eclipse.jst.jsf.context.symbol.IPropertySymbol" volatile="true"
     * @generated
     */
    EList getProperties();

    /**
     * Returns the value of the '<em><b>Type Signature</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Signature</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Signature</em>' attribute.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getITypeDescriptor_TypeSignature()
     * @model default="" changeable="false" volatile="true"
     * @generated
     */
    String getTypeSignature();

    /**
     * Returns the value of the '<em><b>Super Type Signatures</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Type Signatures</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Type Signatures</em>' attribute list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getITypeDescriptor_SuperTypeSignatures()
     * @model type="java.lang.String" volatile="true"
     * @generated
     */
    EList getSuperTypeSignatures();

    /**
     * Returns the value of the '<em><b>Interface Type Signatures</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interface Type Signatures</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interface Type Signatures</em>' attribute list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getITypeDescriptor_InterfaceTypeSignatures()
     * @model type="java.lang.String" volatile="true"
     * @generated
     */
    EList getInterfaceTypeSignatures();

    /**
     * Returns the value of the '<em><b>Type Signature Delegate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Signature Delegate</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Signature Delegate</em>' attribute.
     * @see #setTypeSignatureDelegate(String)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getITypeDescriptor_TypeSignatureDelegate()
     * @model
     * @generated
     */
    String getTypeSignatureDelegate();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getTypeSignatureDelegate <em>Type Signature Delegate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Signature Delegate</em>' attribute.
     * @see #getTypeSignatureDelegate()
     * @generated
     */
    void setTypeSignatureDelegate(String value);

    /**
     * Returns the value of the '<em><b>Methods</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.IMethodSymbol}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Methods</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Methods</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getITypeDescriptor_Methods()
     * @model type="org.eclipse.jst.jsf.context.symbol.IMethodSymbol" volatile="true"
     * @generated
     */
    EList getMethods();

    /**
     * <!-- begin-user-doc -->
     * @param typeSignature 
     * @return true if the type descriptor's underlying type would resolve true == (type instanceof typeSignature)
     * 
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean instanceOf(String typeSignature);

    /**
     * <!-- begin-user-doc -->
     * @return true iff this type is an array of something 
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	boolean isArray();

    /**
     * <!-- begin-user-doc -->
     * @return a symbol representing an element of this array.  May
     * throw an exception or return null if isArray() == false 
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	IObjectSymbol getArrayElement();

}
