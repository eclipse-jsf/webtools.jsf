/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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
 * $Id: TagId.java,v 1.1 2007/02/28 21:16:02 cbateman Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Id</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId#getUri <em>Uri</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getTagId()
 * @model
 * @generated
 */
public interface TagId extends EObject {
    /**
     * Returns the value of the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Uri</em>' attribute.
     * @see #setUri(String)
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getTagId_Uri()
     * @model extendedMetaData="kind='element'"
     * @generated
     */
    String getUri();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId#getUri <em>Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Uri</em>' attribute.
     * @see #getUri()
     * @generated
     */
    void setUri(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getTagId_Name()
     * @model extendedMetaData="kind='element'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TagId
