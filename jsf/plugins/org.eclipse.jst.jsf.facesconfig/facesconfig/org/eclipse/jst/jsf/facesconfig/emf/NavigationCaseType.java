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
 * A representation of the model object '<em><b>Navigation Case Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromAction <em>From Action</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromOutcome <em>From Outcome</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getToViewId <em>To View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getRedirect <em>Redirect</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType()
 * @model extendedMetaData="name='navigation-case_._type' kind='elementOnly'"
 * @generated
 */
public interface NavigationCaseType extends EObject {
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_Description()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_DisplayName()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_Icon()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
     *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
     * @generated
     */
	EList getIcon();

    /**
     * Returns the value of the '<em><b>From Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>From Action</em>' containment reference.
     * @see #setFromAction(FromActionType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_FromAction()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='from-action' namespace='##targetNamespace'"
     * @generated
     */
	FromActionType getFromAction();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromAction <em>From Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>From Action</em>' containment reference.
     * @see #getFromAction()
     * @generated
     */
	void setFromAction(FromActionType value);

    /**
     * Returns the value of the '<em><b>From Outcome</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Outcome</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>From Outcome</em>' containment reference.
     * @see #setFromOutcome(FromOutcomeType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_FromOutcome()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='from-outcome' namespace='##targetNamespace'"
     * @generated
     */
	FromOutcomeType getFromOutcome();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromOutcome <em>From Outcome</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>From Outcome</em>' containment reference.
     * @see #getFromOutcome()
     * @generated
     */
	void setFromOutcome(FromOutcomeType value);

    /**
     * Returns the value of the '<em><b>To View Id</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To View Id</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>To View Id</em>' containment reference.
     * @see #setToViewId(ToViewIdType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_ToViewId()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='to-view-id' namespace='##targetNamespace'"
     * @generated
     */
	ToViewIdType getToViewId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getToViewId <em>To View Id</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>To View Id</em>' containment reference.
     * @see #getToViewId()
     * @generated
     */
	void setToViewId(ToViewIdType value);

    /**
     * Returns the value of the '<em><b>Redirect</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redirect</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Redirect</em>' containment reference.
     * @see #setRedirect(RedirectType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_Redirect()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='redirect' namespace='##targetNamespace'"
     * @generated
     */
	RedirectType getRedirect();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getRedirect <em>Redirect</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Redirect</em>' containment reference.
     * @see #getRedirect()
     * @generated
     */
	void setRedirect(RedirectType value);

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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getNavigationCaseType_Id()
     * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

} // NavigationCaseType
