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
 * A representation of the model object '<em><b>Map Entries Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getKeyClass <em>Key Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getValueClass <em>Value Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getMapEntry <em>Map Entry</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getMapEntriesType()
 * @model extendedMetaData="name='map-entries_._type' kind='elementOnly'"
 * @generated
 */
public interface MapEntriesType extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * Returns the value of the '<em><b>Key Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Class</em>' containment reference.
	 * @see #setKeyClass(KeyClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getMapEntriesType_KeyClass()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='key-class' namespace='##targetNamespace'"
	 * @generated
	 */
	KeyClassType getKeyClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getKeyClass <em>Key Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Class</em>' containment reference.
	 * @see #getKeyClass()
	 * @generated
	 */
	void setKeyClass(KeyClassType value);

	/**
	 * Returns the value of the '<em><b>Value Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Class</em>' containment reference.
	 * @see #setValueClass(ValueClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getMapEntriesType_ValueClass()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='value-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ValueClassType getValueClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getValueClass <em>Value Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Class</em>' containment reference.
	 * @see #getValueClass()
	 * @generated
	 */
	void setValueClass(ValueClassType value);

	/**
	 * Returns the value of the '<em><b>Map Entry</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.MapEntryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Entry</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Entry</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getMapEntriesType_MapEntry()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.MapEntryType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='map-entry' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getMapEntry();

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getMapEntriesType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // MapEntriesType
