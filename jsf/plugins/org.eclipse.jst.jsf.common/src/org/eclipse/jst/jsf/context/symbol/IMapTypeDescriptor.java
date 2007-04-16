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

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMap Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#getMapSource <em>Map Source</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#isImmutable <em>Immutable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIMapTypeDescriptor()
 * @model
 * @generated
 */
public interface IMapTypeDescriptor extends ITypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

    /**
     * Returns the value of the '<em><b>Map Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Map Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Map Source</em>' attribute.
     * @see #setMapSource(Map)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIMapTypeDescriptor_MapSource()
     * @model
     * @generated
     */
    Map getMapSource();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#getMapSource <em>Map Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Map Source</em>' attribute.
     * @see #getMapSource()
     * @generated
     */
    void setMapSource(Map value);

    /**
     * Returns the value of the '<em><b>Immutable</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Immutable</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Immutable</em>' attribute.
     * @see #setImmutable(boolean)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIMapTypeDescriptor_Immutable()
     * @model default="true"
     * @generated
     */
    boolean isImmutable();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#isImmutable <em>Immutable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Immutable</em>' attribute.
     * @see #isImmutable()
     * @generated
     */
    void setImmutable(boolean value);

} // IMapTypeDescriptor
