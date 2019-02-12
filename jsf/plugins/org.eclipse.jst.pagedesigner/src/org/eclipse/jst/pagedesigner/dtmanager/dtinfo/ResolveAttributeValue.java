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
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolve Attribute Value</b></em>'.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue#getAttributeName <em>Attribute Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getResolveAttributeValue()
 * @model
 * @generated
 */
public interface ResolveAttributeValue extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Attribute Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Name</em>' attribute.
	 * @see #setAttributeName(String)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getResolveAttributeValue_AttributeName()
	 * @model required="true"
	 * @generated
	 */
	String getAttributeName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue#getAttributeName <em>Attribute Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Name</em>' attribute.
	 * @see #getAttributeName()
	 * @generated
	 */
	void setAttributeName(String value);

} // ResolveAttributeValue
