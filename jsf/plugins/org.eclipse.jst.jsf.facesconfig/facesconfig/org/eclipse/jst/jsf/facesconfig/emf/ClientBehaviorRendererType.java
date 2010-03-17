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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Client Behavior Renderer Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererType <em>Client Behavior Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererClass <em>Client Behavior Renderer Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getClientBehaviorRendererType()
 * @model extendedMetaData="name='client-behavior-renderer_._type' kind='elementOnly'"
 * @generated
 */
public interface ClientBehaviorRendererType extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Client Behavior Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client Behavior Renderer Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client Behavior Renderer Type</em>' containment reference.
	 * @see #setClientBehaviorRendererType(ClientBehaviorRendererTypeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getClientBehaviorRendererType_ClientBehaviorRendererType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='client-behavior-renderer-type' namespace='##targetNamespace'"
	 * @generated
	 */
	ClientBehaviorRendererTypeType getClientBehaviorRendererType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererType <em>Client Behavior Renderer Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Client Behavior Renderer Type</em>' containment reference.
	 * @see #getClientBehaviorRendererType()
	 * @generated
	 */
	void setClientBehaviorRendererType(ClientBehaviorRendererTypeType value);

	/**
	 * Returns the value of the '<em><b>Client Behavior Renderer Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client Behavior Renderer Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client Behavior Renderer Class</em>' containment reference.
	 * @see #setClientBehaviorRendererClass(ClientBehaviorRendererClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getClientBehaviorRendererType_ClientBehaviorRendererClass()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='client-behavior-renderer-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ClientBehaviorRendererClassType getClientBehaviorRendererClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererClass <em>Client Behavior Renderer Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Client Behavior Renderer Class</em>' containment reference.
	 * @see #getClientBehaviorRendererClass()
	 * @generated
	 */
	void setClientBehaviorRendererClass(ClientBehaviorRendererClassType value);

} // ClientBehaviorRendererType
