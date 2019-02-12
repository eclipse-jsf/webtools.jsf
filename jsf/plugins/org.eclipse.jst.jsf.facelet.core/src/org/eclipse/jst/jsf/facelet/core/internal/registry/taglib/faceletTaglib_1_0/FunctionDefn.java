/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FunctionDefn.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn#getFunctionClass <em>Function Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn#getFunctionSignature <em>Function Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFunctionDefn()
 * @model
 * @generated
 */
public interface FunctionDefn extends EObject
{
    /**
     * Returns the value of the '<em><b>Function Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function Name</em>' attribute.
     * @see #setFunctionName(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFunctionDefn_FunctionName()
     * @model
     * @generated
     */
    String getFunctionName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn#getFunctionName <em>Function Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Name</em>' attribute.
     * @see #getFunctionName()
     * @generated
     */
    void setFunctionName(String value);

    /**
     * Returns the value of the '<em><b>Function Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function Class</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function Class</em>' attribute.
     * @see #setFunctionClass(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFunctionDefn_FunctionClass()
     * @model
     * @generated
     */
    String getFunctionClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn#getFunctionClass <em>Function Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Class</em>' attribute.
     * @see #getFunctionClass()
     * @generated
     */
    void setFunctionClass(String value);

    /**
     * Returns the value of the '<em><b>Function Signature</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function Signature</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function Signature</em>' attribute.
     * @see #setFunctionSignature(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFunctionDefn_FunctionSignature()
     * @model
     * @generated
     */
    String getFunctionSignature();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn#getFunctionSignature <em>Function Signature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Signature</em>' attribute.
     * @see #getFunctionSignature()
     * @generated
     */
    void setFunctionSignature(String value);

} // FunctionDefn
