/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
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
 * $Id: ValidatorTypeInfo_.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validator Type Info </b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_#getValidatorId <em>Validator Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getValidatorTypeInfo_()
 * @model
 * @generated
 */
public interface ValidatorTypeInfo_ extends ClassTypeInfo_
{
    /**
     * Returns the value of the '<em><b>Validator Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validator Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validator Id</em>' attribute.
     * @see #setValidatorId(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getValidatorTypeInfo__ValidatorId()
     * @model extendedMetaData="kind='element' name='validatorId'"
     * @generated
     */
    String getValidatorId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_#getValidatorId <em>Validator Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validator Id</em>' attribute.
     * @see #getValidatorId()
     * @generated
     */
    void setValidatorId(String value);

} // ValidatorTypeInfo_
