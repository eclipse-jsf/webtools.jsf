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
 * $Id: UserVisibleTaglibObject.java,v 1.1 2010/03/18 06:24:37 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Visible Taglib Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject#getIcon <em>Icon</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getUserVisibleTaglibObject()
 * @model abstract="true"
 * @generated
 */
public interface UserVisibleTaglibObject extends EObject
{
    /**
     * Returns the value of the '<em><b>Description</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getUserVisibleTaglibObject_Description()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
     * @generated
     */
    EList<Description> getDescription();

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Name</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getUserVisibleTaglibObject_DisplayName()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='display-name' namespace='##targetNamespace'"
     * @generated
     */
    EList<DisplayName> getDisplayName();

    /**
     * Returns the value of the '<em><b>Icon</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Icon</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Icon</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getUserVisibleTaglibObject_Icon()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
     * @generated
     */
    EList<Icon> getIcon();

    /**
     * <!-- begin-user-doc -->
     * @param language 
     * @return 
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Iterates through all descriptions and returns a concatenated string of
     * all descriptions for the specified language.
     * Passing the empty string indicates descriptions with no locale.
     * Passing null is invalid and will cause an NPE to be thrown.
     * @param separationString
     *   A string that will be used to separate each individual description after it
     *   has been trimmed but before it is concatenated to the result.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    String getDescription(String language, String separationString);

    /**
     * <!-- begin-user-doc -->
     * @return 
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Equivalent to getDescription("").
     * @param separationString
     *   A string that will be used to separate each individual display-name after it
     *   has been trimmed but before it is concatenated to the result.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    String getDefaultDescription(String separationString);

    /**
     * <!-- begin-user-doc -->
     * @param language 
     * @return 
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Iterates through all display names and returns a concatenated string of
     * all display names for the specified language.  Passing null indicates the use of the current locale.
     * Passing the empty string indicates descriptions with no locale.
     * @param separationString
     *   A string that will be used to separate each individual display-name after it
     *   has been trimmed but before it is concatenated to the result.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    String getDisplayName(String language, String separationString);

    /**
     * <!-- begin-user-doc -->
     * @return 
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Equivalent to getDisplayName("").
     * @param separationString
     *   A string that will be used to separate each individual display-name after it
     *   has been trimmed but before it is concatenated to the result.
     * <!-- end-model-doc -->
     * @model
     * @generated
     */
    String getDefaultDisplayName(String separationString);

} // UserVisibleTaglibObject
