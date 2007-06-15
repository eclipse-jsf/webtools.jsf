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
import org.eclipse.jdt.core.IType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IJava Type Descriptor2</b></em>'.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getBeanProperties <em>Bean Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getBeanMethods <em>Bean Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getArrayCount <em>Array Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIJavaTypeDescriptor2()
 * @model
 * @generated
 */
public interface IJavaTypeDescriptor2 extends ITypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(IType)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIJavaTypeDescriptor2_Type()
     * @model dataType="org.eclipse.jst.jsf.context.symbol.IType"
     * @generated
     */
	IType getType();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
	void setType(IType value);

    /**
     * Returns the value of the '<em><b>Bean Properties</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bean Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Bean Properties</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIJavaTypeDescriptor2_BeanProperties()
     * @model type="org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol" volatile="true"
     * @generated
     */
	EList getBeanProperties();

    /**
     * Returns the value of the '<em><b>Bean Methods</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bean Methods</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Bean Methods</em>' reference list.
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIJavaTypeDescriptor2_BeanMethods()
     * @model type="org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol" volatile="true"
     * @generated
     */
	EList getBeanMethods();

    /**
     * Returns the value of the '<em><b>Array Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Array Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Array Count</em>' attribute.
     * @see #setArrayCount(int)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIJavaTypeDescriptor2_ArrayCount()
     * @model
     * @generated
     */
    int getArrayCount();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getArrayCount <em>Array Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * records the array nesting of the type.  IType doesn't encapsulate
     * array types. So if this type is an array then type will represent
     * the base element and this value will be > 0.  If not an array, then
     * _arrayCount is always 0. 
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Array Count</em>' attribute.
     * @see #getArrayCount()
     * @generated
     */
    void setArrayCount(int value);

    /**
     * <!-- begin-user-doc -->
     * @param resolvedTypeSignature 
     * @return the resolved type or null. 
     * <!-- end-user-doc -->
     * @model dataType="org.eclipse.jst.jsf.context.symbol.IType"
     * @generated
     */
    IType resolveType(String resolvedTypeSignature);

} // IJavaTypeDescriptor2
