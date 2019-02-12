/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PF Page</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getSmallicon <em>Smallicon</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getLargeicon <em>Largeicon</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFPage()
 * @model
 * @generated
 */
public interface PageflowPage extends PageflowNode {
	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFPage_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getPath <em>Path</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Smallicon</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Smallicon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Smallicon</em>' attribute.
	 * @see #setSmallicon(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFPage_Smallicon()
	 * @model
	 * @generated
	 */
	String getSmallicon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getSmallicon <em>Smallicon</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Smallicon</em>' attribute.
	 * @see #getSmallicon()
	 * @generated
	 */
	void setSmallicon(String value);

	/**
	 * Returns the value of the '<em><b>Largeicon</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Largeicon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Largeicon</em>' attribute.
	 * @see #setLargeicon(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFPage_Largeicon()
	 * @model
	 * @generated
	 */
	String getLargeicon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getLargeicon <em>Largeicon</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Largeicon</em>' attribute.
	 * @see #getLargeicon()
	 * @generated
	 */
	void setLargeicon(String value);

}
// PFPage
