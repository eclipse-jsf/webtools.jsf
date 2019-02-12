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
 * $Id: FaceletTaglibTagConverter.java,v 1.1 2010/03/18 06:24:29 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Tag Converter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 Within a tag element, the converter element encapsulates
 *                 information specific to a JSF Converter.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterExtension <em>Converter Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagConverter()
 * @model extendedMetaData="name='facelet-taglib-tag-converterType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglibTagConverter extends UserVisibleTaglibObject
{
    /**
     * Returns the value of the '<em><b>Converter Id</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Converter Id</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Id</em>' containment reference.
     * @see #setConverterId(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagConverter_ConverterId()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='converter-id' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getConverterId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getConverterId <em>Converter Id</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter Id</em>' containment reference.
     * @see #getConverterId()
     * @generated
     */
    void setConverterId(IdentifiableStringValue value);

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
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagConverter_HandlerClass()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='handler-class' namespace='##targetNamespace'"
     * @generated
     */
    FullyQualifiedClass getHandlerClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter#getHandlerClass <em>Handler Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Handler Class</em>' containment reference.
     * @see #getHandlerClass()
     * @generated
     */
    void setHandlerClass(FullyQualifiedClass value);

    /**
     * Returns the value of the '<em><b>Converter Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Converter Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagConverter_ConverterExtension()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='converter-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList<FaceletTaglibTagConverterExtension> getConverterExtension();

} // FaceletTaglibTagConverter
