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
 * A representation of the model object '<em><b>Redirect Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getViewParam <em>View Param</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#isIncludeViewParams <em>Include View Params</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRedirectType()
 * @model extendedMetaData="name='redirect_._type' kind='empty'"
 * @generated
 */
public interface RedirectType extends EObject {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * Returns the value of the '<em><b>View Param</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Param</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Param</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRedirectType_ViewParam()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType" containment="true"
	 *        extendedMetaData="kind='element' name='view-param' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getViewParam();

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
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRedirectType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

				/**
	 * Returns the value of the '<em><b>Include View Params</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include View Params</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include View Params</em>' attribute.
	 * @see #setIncludeViewParams(boolean)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getRedirectType_IncludeViewParams()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='include-view-params' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIncludeViewParams();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#isIncludeViewParams <em>Include View Params</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Include View Params</em>' attribute.
	 * @see #isIncludeViewParams()
	 * @generated
	 */
	void setIncludeViewParams(boolean value);

} // RedirectType
