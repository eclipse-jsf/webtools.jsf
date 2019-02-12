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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getBefore <em>Before</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getAfter <em>After</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getOrderingType()
 * @model extendedMetaData="name='ordering_._type' kind='elementOnly'"
 * @generated
 */
public interface OrderingType extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Before</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Before</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Before</em>' containment reference.
	 * @see #setBefore(OrderingOrderingType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getOrderingType_Before()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='before' namespace='##targetNamespace'"
	 * @generated
	 */
	OrderingOrderingType getBefore();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getBefore <em>Before</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before</em>' containment reference.
	 * @see #getBefore()
	 * @generated
	 */
	void setBefore(OrderingOrderingType value);

	/**
	 * Returns the value of the '<em><b>After</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>After</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After</em>' containment reference.
	 * @see #setAfter(OrderingOrderingType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getOrderingType_After()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='after' namespace='##targetNamespace'"
	 * @generated
	 */
	OrderingOrderingType getAfter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getAfter <em>After</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After</em>' containment reference.
	 * @see #getAfter()
	 * @generated
	 */
	void setAfter(OrderingOrderingType value);

} // OrderingType
