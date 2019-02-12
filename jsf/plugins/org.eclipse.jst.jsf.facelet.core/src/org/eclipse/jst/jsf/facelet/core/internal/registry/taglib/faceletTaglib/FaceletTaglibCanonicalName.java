/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibCanonicalName.java,v 1.1 2010/03/18 06:24:29 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Canonical Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *                 Defines the canonical name of a tag or attribute being
 *                 defined.
 * 
 *                 The name must conform to the lexical rules for an NCName
 * 
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibCanonicalName()
 * @model extendedMetaData="name='facelet-taglib-canonical-nameType' kind='simple'"
 * @generated
 */
public interface FaceletTaglibCanonicalName extends EObject
{
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibCanonicalName_Value()
     * @model dataType="org.eclipse.emf.ecore.xml.type.NCName"
     *        extendedMetaData="name=':0' kind='simple'"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

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
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibCanonicalName_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // FaceletTaglibCanonicalName
