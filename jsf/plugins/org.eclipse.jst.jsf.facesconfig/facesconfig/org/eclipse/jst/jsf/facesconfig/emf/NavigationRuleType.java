/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * A representation of the model object '<em><b>Navigation Rule Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getFromViewId <em>From View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getNavigationCase <em>Navigation Case</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getNavigationRuleExtension <em>Navigation Rule Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType()
 * @model extendedMetaData="name='navigation-rule_._type' kind='elementOnly'"
 * @generated
 */
public interface NavigationRuleType extends EObject {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_Description()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_DisplayName()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_Icon()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
     *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
     * @generated
     */
	EList getIcon();

    /**
     * Returns the value of the '<em><b>From View Id</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From View Id</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>From View Id</em>' containment reference.
     * @see #setFromViewId(FromViewIdType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_FromViewId()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='from-view-id' namespace='##targetNamespace'"
     * @generated
     */
	FromViewIdType getFromViewId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getFromViewId <em>From View Id</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>From View Id</em>' containment reference.
     * @see #getFromViewId()
     * @generated
     */
	void setFromViewId(FromViewIdType value);

    /**
     * Returns the value of the '<em><b>Navigation Case</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation Case</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Navigation Case</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_NavigationCase()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType" containment="true"
     *        extendedMetaData="kind='element' name='navigation-case' namespace='##targetNamespace'"
     * @generated
     */
	EList getNavigationCase();

    /**
     * Returns the value of the '<em><b>Navigation Rule Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Navigation Rule Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Navigation Rule Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_NavigationRuleExtension()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType" containment="true"
     *        extendedMetaData="kind='element' name='navigation-rule-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList getNavigationRuleExtension();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationRuleType_Id()
     * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

} // NavigationRuleType
