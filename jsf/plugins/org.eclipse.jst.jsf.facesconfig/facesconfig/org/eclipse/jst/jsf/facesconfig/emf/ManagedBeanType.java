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
 * A representation of the model object '<em><b>Managed Bean Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanName <em>Managed Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanClass <em>Managed Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanScope <em>Managed Bean Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedProperty <em>Managed Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getMapEntries <em>Map Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getListEntries <em>List Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanExtension <em>Managed Bean Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType()
 * @model extendedMetaData="name='managed-bean_._type' kind='elementOnly'"
 * @generated
 */
public interface ManagedBeanType extends EObject {
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_Description()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_DisplayName()
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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_Icon()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
     *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
     * @generated
     */
	EList getIcon();

    /**
     * Returns the value of the '<em><b>Managed Bean Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Bean Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Managed Bean Name</em>' containment reference.
     * @see #setManagedBeanName(ManagedBeanNameType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_ManagedBeanName()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='managed-bean-name' namespace='##targetNamespace'"
     * @generated
     */
	ManagedBeanNameType getManagedBeanName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanName <em>Managed Bean Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Managed Bean Name</em>' containment reference.
     * @see #getManagedBeanName()
     * @generated
     */
	void setManagedBeanName(ManagedBeanNameType value);

    /**
     * Returns the value of the '<em><b>Managed Bean Class</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Bean Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Managed Bean Class</em>' containment reference.
     * @see #setManagedBeanClass(ManagedBeanClassType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_ManagedBeanClass()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='managed-bean-class' namespace='##targetNamespace'"
     * @generated
     */
	ManagedBeanClassType getManagedBeanClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanClass <em>Managed Bean Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Managed Bean Class</em>' containment reference.
     * @see #getManagedBeanClass()
     * @generated
     */
	void setManagedBeanClass(ManagedBeanClassType value);

    /**
     * Returns the value of the '<em><b>Managed Bean Scope</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Bean Scope</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Managed Bean Scope</em>' containment reference.
     * @see #setManagedBeanScope(ManagedBeanScopeType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_ManagedBeanScope()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='managed-bean-scope' namespace='##targetNamespace'"
     * @generated
     */
	ManagedBeanScopeType getManagedBeanScope();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanScope <em>Managed Bean Scope</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Managed Bean Scope</em>' containment reference.
     * @see #getManagedBeanScope()
     * @generated
     */
	void setManagedBeanScope(ManagedBeanScopeType value);

    /**
     * Returns the value of the '<em><b>Managed Property</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Managed Property</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_ManagedProperty()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType" containment="true"
     *        extendedMetaData="kind='element' name='managed-property' namespace='##targetNamespace'"
     * @generated
     */
	EList getManagedProperty();

    /**
     * Returns the value of the '<em><b>Map Entries</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Entries</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Map Entries</em>' containment reference.
     * @see #setMapEntries(MapEntriesType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_MapEntries()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='map-entries' namespace='##targetNamespace'"
     * @generated
     */
	MapEntriesType getMapEntries();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getMapEntries <em>Map Entries</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Map Entries</em>' containment reference.
     * @see #getMapEntries()
     * @generated
     */
	void setMapEntries(MapEntriesType value);

    /**
     * Returns the value of the '<em><b>List Entries</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Entries</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>List Entries</em>' containment reference.
     * @see #setListEntries(ListEntriesType)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_ListEntries()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='list-entries' namespace='##targetNamespace'"
     * @generated
     */
	ListEntriesType getListEntries();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getListEntries <em>List Entries</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>List Entries</em>' containment reference.
     * @see #getListEntries()
     * @generated
     */
	void setListEntries(ListEntriesType value);

    /**
     * Returns the value of the '<em><b>Managed Bean Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Managed Bean Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Managed Bean Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_ManagedBeanExtension()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType" containment="true"
     *        extendedMetaData="kind='element' name='managed-bean-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList getManagedBeanExtension();

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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedBeanType_Id()
     * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

} // ManagedBeanType
