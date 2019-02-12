/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.ReferenceElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getComment <em>Comment</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getX <em>X</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getY <em>Y</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getWidth <em>Width</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getHeight <em>Height</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement()
 * @model abstract="true"
 * @generated
 */
public interface PageflowElement extends EObject {
	/**
	 * The pageflow model is based on faces-config model, its elements will
	 * reference faces-config elements with ReferenceElement.
	 * @return the ref element
	 * 
	 * @generated NOT
	 */
	public ReferenceElement getFCElements();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. The
	 * default value is <code>"unnamed"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_Name()
	 * @model default="unnamed"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getName <em>Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_Comment()
	 * @model
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getComment <em>Comment</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute. The default
	 * value is <code>"0"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_X()
	 * @model default="0"
	 * @generated
	 */
	int getX();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getX <em>X</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(int value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute. The default
	 * value is <code>"0"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_Y()
	 * @model default="0"
	 * @generated
	 */
	int getY();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getY <em>Y</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(int value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute. The
	 * default value is <code>"-1"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_Width()
	 * @model default="-1"
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getWidth <em>Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute. The
	 * default value is <code>"-1"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_Height()
	 * @model default="-1"
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getHeight <em>Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPageflowElement_Id()
	 * @model id="true" required="true" volatile="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Reference Link</b></em>' attribute.
	 * The default value is <code>""</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Link</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Reference Link</em>' attribute.
	 * @see #setReferenceLink(String)
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='referenceLink'"
	 * @generated
	 */
	String getReferenceLink();

	/**
	 * Sets the value of the '{@link PageflowElement#getReferenceLink() <em>Reference Link</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Reference Link</em>' attribute.
	 * @see #getReferenceLink()
	 * @generated
	 */
	void setReferenceLink(String value);

	/**
	 * @param nofitification 
	 * @generated NOT
	 */
	void notifyModelChanged(Notification nofitification);

	/**
	 * When the model is modified, the adapters or reference elements should be
	 * updated.
	 * 
	 * @generated NOT
	 */
	void update();

	/**
	 * @generated NOT
	 */
	void dispose();
}
// PageflowElement
