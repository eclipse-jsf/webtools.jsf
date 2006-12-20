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
 * A representation of the model object '<em><b>Dynamic Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getTextContent <em>Text Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDynamicElement()
 * @model
 * @generated
 */
public interface DynamicElement extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * Returns the value of the '<em><b>Child Nodes</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Child Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Nodes</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDynamicElement_ChildNodes()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.DynamicElement" containment="true"
     * @generated
     */
    EList getChildNodes();

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDynamicElement_Attributes()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute"
     * @generated
     */
    EList getAttributes();

    /**
     * Returns the value of the '<em><b>Text Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text Content</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text Content</em>' attribute.
     * @see #setTextContent(String)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDynamicElement_TextContent()
     * @model
     * @generated
     */
    String getTextContent();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getTextContent <em>Text Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Content</em>' attribute.
     * @see #getTextContent()
     * @generated
     */
    void setTextContent(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDynamicElement_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // DynamicElement
