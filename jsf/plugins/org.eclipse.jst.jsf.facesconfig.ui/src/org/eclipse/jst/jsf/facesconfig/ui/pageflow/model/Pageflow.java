/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pageflow</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getLinks <em>Links</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getConfigfile <em>Configfile</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflow()
 * @model
 * @generated
 */
public interface Pageflow extends PageflowElement {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getPageflow <em>Pageflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflow_Nodes()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getPageflow
	 * @model type="org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode"
	 *        opposite="pageflow" containment="true"
	 * @generated
	 */
	EList getNodes();

	/**
	 * Returns the value of the '<em><b>Links</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink}. It
	 * is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getPageflow <em>Pageflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Links</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflow_Links()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getPageflow
	 * @model type="org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink"
	 *        opposite="pageflow" containment="true"
	 * @generated
	 */
	EList getLinks();

	/**
	 * Returns the value of the '<em><b>Configfile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configfile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Configfile</em>' attribute.
	 * @see #setConfigfile(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflow_Configfile()
	 * @model
	 * @generated
	 */
	String getConfigfile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getConfigfile <em>Configfile</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Configfile</em>' attribute.
	 * @see #getConfigfile()
	 * @generated
	 */
	void setConfigfile(String value);

	/**
	 * 
	 * @param source 
	 * @param target 
	 * @param link 
	 * @generated NOT
	 */
	public void connect(PageflowNode source, PageflowNode target, PageflowLink link);

	/**
	 * 
	 * @param nodeType 
	 * @return the default node name
	 * @generated NOT
	 */
	public String getDefaultNodeName(Class nodeType);
}
// Pageflow
