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
 * A representation of the model object '<em><b>Behavior Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorId <em>Behavior Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorClass <em>Behavior Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorExtension <em>Behavior Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType()
 * @model extendedMetaData="name='behavior_._type' kind='elementOnly'"
 * @generated
 */
public interface BehaviorType extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DescriptionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_Description()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.DescriptionType" containment="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getDescription();

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_DisplayName()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType" containment="true"
	 *        extendedMetaData="kind='element' name='display-name' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getDisplayName();

	/**
	 * Returns the value of the '<em><b>Icon</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.IconType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_Icon()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
	 *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getIcon();

	/**
	 * Returns the value of the '<em><b>Behavior Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior Id</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Id</em>' containment reference.
	 * @see #setBehaviorId(BehaviorIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_BehaviorId()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='behavior-id' namespace='##targetNamespace'"
	 * @generated
	 */
	BehaviorIdType getBehaviorId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorId <em>Behavior Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Id</em>' containment reference.
	 * @see #getBehaviorId()
	 * @generated
	 */
	void setBehaviorId(BehaviorIdType value);

	/**
	 * Returns the value of the '<em><b>Behavior Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Class</em>' containment reference.
	 * @see #setBehaviorClass(BehaviorClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_BehaviorClass()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='behavior-class' namespace='##targetNamespace'"
	 * @generated
	 */
	BehaviorClassType getBehaviorClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorClass <em>Behavior Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Class</em>' containment reference.
	 * @see #getBehaviorClass()
	 * @generated
	 */
	void setBehaviorClass(BehaviorClassType value);

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_Attribute()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.AttributeType" containment="true"
	 *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getAttribute();

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_Property()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.PropertyType" containment="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getProperty();

	/**
	 * Returns the value of the '<em><b>Behavior Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior Extension</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Extension</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getBehaviorType_BehaviorExtension()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType" containment="true"
	 *        extendedMetaData="kind='element' name='behavior-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getBehaviorExtension();

} // BehaviorType
