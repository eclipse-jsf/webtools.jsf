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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getPageflow <em>Pageflow</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getOutlinks <em>Outlinks</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getInlinks <em>Inlinks</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowNode()
 * @model abstract="true"
 * @generated
 */
public interface PageflowNode extends PageflowElement {
	/**
	 * Returns the value of the '<em><b>Pageflow</b></em>' container
	 * reference. It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pageflow</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pageflow</em>' container reference.
	 * @see #setPageflow(Pageflow)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowNode_Pageflow()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getNodes
	 * @model opposite="nodes" required="true"
	 * @generated
	 */
	Pageflow getPageflow();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getPageflow <em>Pageflow</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Pageflow</em>' container
	 *            reference.
	 * @see #getPageflow()
	 * @generated
	 */
	void setPageflow(Pageflow value);

	/**
	 * Returns the value of the '<em><b>Outlinks</b></em>' reference list.
	 * The list contents are of type
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink}. It
	 * is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outlinks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Outlinks</em>' reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowNode_Outlinks()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSource
	 * @model type="org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink"
	 *        opposite="source"
	 * @generated
	 */
	EList getOutlinks();

	/**
	 * Returns the value of the '<em><b>Inlinks</b></em>' reference list.
	 * The list contents are of type
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink}. It
	 * is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inlinks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Inlinks</em>' reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowNode_Inlinks()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getTarget
	 * @model type="org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink"
	 *        opposite="target"
	 * @generated
	 */
	EList getInlinks();

	/**
	 * @generated NOT
	 */
	PageflowLink findLinkTo(PageflowNode target);

}
// PageflowNode
