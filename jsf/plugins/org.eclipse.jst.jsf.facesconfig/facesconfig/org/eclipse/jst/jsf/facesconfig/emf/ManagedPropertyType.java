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
 * A representation of the model object '<em><b>Managed Property Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyClass <em>Property Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getMapEntries <em>Map Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getNullValue <em>Null Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getListEntries <em>List Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType()
 * @model extendedMetaData="name='managed-property_._type' kind='elementOnly'"
 * @generated
 */
public interface ManagedPropertyType extends EObject {
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_Description()
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_DisplayName()
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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_Icon()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.IconType" containment="true"
	 *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getIcon();

    /**
	 * Returns the value of the '<em><b>Property Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Name</em>' containment reference.
	 * @see #setPropertyName(PropertyNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_PropertyName()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='property-name' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyNameType getPropertyName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyName <em>Property Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Name</em>' containment reference.
	 * @see #getPropertyName()
	 * @generated
	 */
	void setPropertyName(PropertyNameType value);

    /**
	 * Returns the value of the '<em><b>Property Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Class</em>' containment reference.
	 * @see #setPropertyClass(PropertyClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_PropertyClass()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='property-class' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyClassType getPropertyClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyClass <em>Property Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Class</em>' containment reference.
	 * @see #getPropertyClass()
	 * @generated
	 */
	void setPropertyClass(PropertyClassType value);

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_MapEntries()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='map-entries' namespace='##targetNamespace'"
	 * @generated
	 */
	MapEntriesType getMapEntries();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getMapEntries <em>Map Entries</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Entries</em>' containment reference.
	 * @see #getMapEntries()
	 * @generated
	 */
	void setMapEntries(MapEntriesType value);

    /**
	 * Returns the value of the '<em><b>Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Null Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Null Value</em>' containment reference.
	 * @see #setNullValue(NullValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_NullValue()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='null-value' namespace='##targetNamespace'"
	 * @generated
	 */
	NullValueType getNullValue();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getNullValue <em>Null Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Null Value</em>' containment reference.
	 * @see #getNullValue()
	 * @generated
	 */
	void setNullValue(NullValueType value);

    /**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(ValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_Value()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='value' namespace='##targetNamespace'"
	 * @generated
	 */
	ValueType getValue();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(ValueType value);

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_ListEntries()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='list-entries' namespace='##targetNamespace'"
	 * @generated
	 */
	ListEntriesType getListEntries();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getListEntries <em>List Entries</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List Entries</em>' containment reference.
	 * @see #getListEntries()
	 * @generated
	 */
	void setListEntries(ListEntriesType value);

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getManagedPropertyType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ManagedPropertyType
