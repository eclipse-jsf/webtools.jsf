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
 * A representation of the model object '<em><b>IObject Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#getTypeDescriptor <em>Type Descriptor</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isReadable <em>Readable</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isWritable <em>Writable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIObjectSymbol()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IObjectSymbol extends ISymbol {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

    /**
     * Returns the value of the '<em><b>Type Descriptor</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Descriptor</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Descriptor</em>' reference.
     * @see #setTypeDescriptor(ITypeDescriptor)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIObjectSymbol_TypeDescriptor()
     * @model
     * @generated
     */
    ITypeDescriptor getTypeDescriptor();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#getTypeDescriptor <em>Type Descriptor</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Descriptor</em>' reference.
     * @see #getTypeDescriptor()
     * @generated
     */
    void setTypeDescriptor(ITypeDescriptor value);

    /**
     * Returns the value of the '<em><b>Readable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Readable</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Readable</em>' attribute.
     * @see #setReadable(boolean)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIObjectSymbol_Readable()
     * @model
     * @generated
     */
    boolean isReadable();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isReadable <em>Readable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Readable</em>' attribute.
     * @see #isReadable()
     * @generated
     */
    void setReadable(boolean value);

    /**
     * Returns the value of the '<em><b>Writable</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Writable</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Writable</em>' attribute.
     * @see #setWritable(boolean)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIObjectSymbol_Writable()
     * @model
     * @generated
     */
    boolean isWritable();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isWritable <em>Writable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Writable</em>' attribute.
     * @see #isWritable()
     * @generated
     */
    void setWritable(boolean value);

    /**
     * <!-- begin-user-doc -->
     * @param typeSignature 
     * @return true if this object can be coerced (is an instanceof) the type
     * specified in the fully qualified typeSignature 
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean supportsCoercion(String typeSignature);

    /**
     * <!-- begin-user-doc -->
     * @param typeSignature 
     * @return a type descriptor supporting a class cast of this object to
     * the request typeSignature, or null if such a cast is not supported.
     * Returns non-null iff supportsCoercion(typeSignature) == false
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    ITypeDescriptor coerce(String typeSignature);

} // IObjectSymbol
