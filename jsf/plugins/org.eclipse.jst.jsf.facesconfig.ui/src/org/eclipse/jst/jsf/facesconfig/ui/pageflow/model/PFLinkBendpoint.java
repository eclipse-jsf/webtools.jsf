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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PF Link Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD1Width <em>D1 Width</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD1Height <em>D1 Height</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD2Width <em>D2 Width</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD2Height <em>D2 Height</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getWeight <em>Weight</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint()
 * @model
 * @generated
 */
public interface PFLinkBendpoint extends EObject {
	/**
	 * Returns the value of the '<em><b>D1 Width</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>D1 Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>D1 Width</em>' attribute.
	 * @see #setD1Width(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint_D1Width()
	 * @model
	 * @generated
	 */
	int getD1Width();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD1Width <em>D1 Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>D1 Width</em>' attribute.
	 * @see #getD1Width()
	 * @generated
	 */
	void setD1Width(int value);

	/**
	 * Returns the value of the '<em><b>D1 Height</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>D1 Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>D1 Height</em>' attribute.
	 * @see #setD1Height(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint_D1Height()
	 * @model
	 * @generated
	 */
	int getD1Height();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD1Height <em>D1 Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>D1 Height</em>' attribute.
	 * @see #getD1Height()
	 * @generated
	 */
	void setD1Height(int value);

	/**
	 * Returns the value of the '<em><b>D2 Width</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>D2 Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>D2 Width</em>' attribute.
	 * @see #setD2Width(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint_D2Width()
	 * @model
	 * @generated
	 */
	int getD2Width();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD2Width <em>D2 Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>D2 Width</em>' attribute.
	 * @see #getD2Width()
	 * @generated
	 */
	void setD2Width(int value);

	/**
	 * Returns the value of the '<em><b>D2 Height</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>D2 Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>D2 Height</em>' attribute.
	 * @see #setD2Height(int)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint_D2Height()
	 * @model
	 * @generated
	 */
	int getD2Height();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getD2Height <em>D2 Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>D2 Height</em>' attribute.
	 * @see #getD2Height()
	 * @generated
	 */
	void setD2Height(int value);

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute. The
	 * default value is <code>"0.5"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(float)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint_Weight()
	 * @model default="0.5"
	 * @generated
	 */
	float getWeight();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getWeight <em>Weight</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(float value);

	/**
	 * Returns the value of the '<em><b>Link</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink#getBendPoints <em>Bend Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Link</em>' container reference.
	 * @see #setLink(PFLink)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage#getPFLinkBendpoint_Link()
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink#getBendPoints
	 * @model opposite="bendPoints" required="true"
	 * @generated
	 */
	PFLink getLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint#getLink <em>Link</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Link</em>' container reference.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(PFLink value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Dimension getFirstRelativeDimension();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Dimension getSecondRelativeDimension();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRelativeDimensions(Dimension dim1, Dimension dim2);

}
// PFLinkBendpoint
