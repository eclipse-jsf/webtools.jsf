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
 * $Id: FaceletTaglibTagBehavior.java,v 1.1 2010/03/18 06:24:37 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Tag Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 Within a tag element, the behavior element encapsulates
 *                 information specific to a JSF Behavior.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorId <em>Behavior Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorExtension <em>Behavior Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagBehavior()
 * @model extendedMetaData="name='facelet-taglib-tag-behaviorType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglibTagBehavior extends UserVisibleTaglibObject
{
    /**
     * Returns the value of the '<em><b>Behavior Id</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior Id</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Behavior Id</em>' containment reference.
     * @see #setBehaviorId(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagBehavior_BehaviorId()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='behavior-id' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getBehaviorId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getBehaviorId <em>Behavior Id</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Behavior Id</em>' containment reference.
     * @see #getBehaviorId()
     * @generated
     */
    void setBehaviorId(IdentifiableStringValue value);

    /**
     * Returns the value of the '<em><b>Handler Class</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Handler Class</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Handler Class</em>' containment reference.
     * @see #setHandlerClass(FullyQualifiedClass)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagBehavior_HandlerClass()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='handler-class' namespace='##targetNamespace'"
     * @generated
     */
    FullyQualifiedClass getHandlerClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior#getHandlerClass <em>Handler Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Handler Class</em>' containment reference.
     * @see #getHandlerClass()
     * @generated
     */
    void setHandlerClass(FullyQualifiedClass value);

    /**
     * Returns the value of the '<em><b>Behavior Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Behavior Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagBehavior_BehaviorExtension()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='behavior-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList<FaceletTaglibTagBehaviorExtension> getBehaviorExtension();

} // FaceletTaglibTagBehavior
