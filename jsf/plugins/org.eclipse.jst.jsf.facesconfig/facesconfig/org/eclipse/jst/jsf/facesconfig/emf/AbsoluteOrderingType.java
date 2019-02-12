/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAbsoluteOrderingType()
 * @model extendedMetaData="name='absolute-ordering_._type' kind='elementOnly'"
 * @generated
 */
public interface AbsoluteOrderingType extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.NameType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAbsoluteOrderingType_Name()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.NameType" containment="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getName();

	/**
	 * Returns the value of the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Others</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Others</em>' containment reference.
	 * @see #setOthers(OrderingOthersType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getAbsoluteOrderingType_Others()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='others' namespace='##targetNamespace'"
	 * @generated
	 */
	OrderingOthersType getOthers();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getOthers <em>Others</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Others</em>' containment reference.
	 * @see #getOthers()
	 * @generated
	 */
	void setOthers(OrderingOthersType value);

} // AbsoluteOrderingType
