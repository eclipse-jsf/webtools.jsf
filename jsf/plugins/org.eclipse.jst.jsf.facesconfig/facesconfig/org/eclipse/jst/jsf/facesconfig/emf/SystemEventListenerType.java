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
 * A representation of the model object '<em><b>System Event Listener Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventListenerClass <em>System Event Listener Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventClass <em>System Event Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSourceClass <em>Source Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getSystemEventListenerType()
 * @model extendedMetaData="name='system-event-listener_._type' kind='elementOnly'"
 * @generated
 */
public interface SystemEventListenerType extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>System Event Listener Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Event Listener Class</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Event Listener Class</em>' containment reference.
	 * @see #setSystemEventListenerClass(SystemEventListenerClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getSystemEventListenerType_SystemEventListenerClass()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='system-event-listener-class' namespace='##targetNamespace'"
	 * @generated
	 */
	SystemEventListenerClassType getSystemEventListenerClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventListenerClass <em>System Event Listener Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Event Listener Class</em>' containment reference.
	 * @see #getSystemEventListenerClass()
	 * @generated
	 */
	void setSystemEventListenerClass(SystemEventListenerClassType value);

	/**
	 * Returns the value of the '<em><b>System Event Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Event Class</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Event Class</em>' containment reference.
	 * @see #setSystemEventClass(SystemEventClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getSystemEventListenerType_SystemEventClass()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='system-event-class' namespace='##targetNamespace'"
	 * @generated
	 */
	SystemEventClassType getSystemEventClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventClass <em>System Event Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Event Class</em>' containment reference.
	 * @see #getSystemEventClass()
	 * @generated
	 */
	void setSystemEventClass(SystemEventClassType value);

	/**
	 * Returns the value of the '<em><b>Source Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Class</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Class</em>' containment reference.
	 * @see #setSourceClass(SourceClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getSystemEventListenerType_SourceClass()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='source-class' namespace='##targetNamespace'"
	 * @generated
	 */
	SourceClassType getSourceClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSourceClass <em>Source Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Class</em>' containment reference.
	 * @see #getSourceClass()
	 * @generated
	 */
	void setSourceClass(SourceClassType value);

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getSystemEventListenerType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SystemEventListenerType
