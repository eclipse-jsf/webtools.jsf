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

import java.util.Vector;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PF Link</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getPageflow <em>Pageflow</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSmallicon <em>Smallicon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getLargeicon <em>Largeicon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getFromaction <em>Fromaction</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getOutcome <em>Outcome</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#isRedirect <em>Redirect</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getBendPoints <em>Bend Points</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.org.eclipse.jst.jsf.facesconfig.editor.pageflow.model.model.PageflowPackage#getPFLink()
 * @model
 * @generated
 */
public interface PageflowLink extends PageflowElement {
	/**
	 * Returns the value of the '<em><b>Pageflow</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pageflow</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pageflow</em>' container reference.
	 * @see #setPageflow(Pageflow)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Pageflow()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getLinks
	 * @model opposite="links" required="true"
	 * @generated
	 */
	Pageflow getPageflow();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getPageflow <em>Pageflow</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pageflow</em>' container reference.
	 * @see #getPageflow()
	 * @generated
	 */
	void setPageflow(Pageflow value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getInlinks <em>Inlinks</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(PageflowNode)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Target()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getInlinks
	 * @model opposite="inlinks" required="true"
	 * @generated
	 */
	PageflowNode getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(PageflowNode value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getOutlinks <em>Outlinks</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(PageflowNode)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Source()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getOutlinks
	 * @model opposite="outlinks" required="true"
	 * @generated
	 */
	PageflowNode getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(PageflowNode value);

	/**
	 * Returns the value of the '<em><b>Smallicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Smallicon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Smallicon</em>' attribute.
	 * @see #setSmallicon(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Smallicon()
	 * @model
	 * @generated
	 */
	String getSmallicon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSmallicon <em>Smallicon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Smallicon</em>' attribute.
	 * @see #getSmallicon()
	 * @generated
	 */
	void setSmallicon(String value);

	/**
	 * Returns the value of the '<em><b>Largeicon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Largeicon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Largeicon</em>' attribute.
	 * @see #setLargeicon(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Largeicon()
	 * @model
	 * @generated
	 */
	String getLargeicon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getLargeicon <em>Largeicon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Largeicon</em>' attribute.
	 * @see #getLargeicon()
	 * @generated
	 */
	void setLargeicon(String value);

	/**
	 * Returns the value of the '<em><b>Fromaction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fromaction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fromaction</em>' attribute.
	 * @see #setFromaction(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Fromaction()
	 * @model
	 * @generated
	 */
	String getFromaction();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getFromaction <em>Fromaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fromaction</em>' attribute.
	 * @see #getFromaction()
	 * @generated
	 */
	void setFromaction(String value);

	/**
	 * Returns the value of the '<em><b>Outcome</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outcome</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outcome</em>' attribute.
	 * @see #setOutcome(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Outcome()
	 * @model
	 * @generated
	 */
	String getOutcome();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getOutcome <em>Outcome</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outcome</em>' attribute.
	 * @see #getOutcome()
	 * @generated
	 */
	void setOutcome(String value);

	/**
	 * Returns the value of the '<em><b>Redirect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redirect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redirect</em>' attribute.
	 * @see #setRedirect(boolean)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_Redirect()
	 * @model
	 * @generated
	 */
	boolean isRedirect();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#isRedirect <em>Redirect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redirect</em>' attribute.
	 * @see #isRedirect()
	 * @generated
	 */
	void setRedirect(boolean value);

	/**
	 * Returns the value of the '<em><b>Bend Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bend Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bend Points</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLink_BendPoints()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getLink
	 * @model type="org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint" opposite="link" containment="true"
	 * @generated
	 */
	EList getBendPoints();

	/**
	 * <!-- begin-user-doc --> insert a new bendpoint to the link <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void insertBendpoint(int index, PageflowLinkBendpoint point);

	/**
	 * <!-- begin-user-doc --> remove the current bendpoint <!-- end-user-doc
	 * -->
	 * 
	 * @generated NOT
	 */
	public void removeBendpoint(int index);

	/**
	 * <!-- begin-user-doc --> Set the current bendpoint <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setBendpoint(int index, PageflowLinkBendpoint point);

	/**
	 * <!-- begin-user-doc --> Set a group of bendpoints to reconstruct the link
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setBendpoints(Vector points);

}
// PFLink
