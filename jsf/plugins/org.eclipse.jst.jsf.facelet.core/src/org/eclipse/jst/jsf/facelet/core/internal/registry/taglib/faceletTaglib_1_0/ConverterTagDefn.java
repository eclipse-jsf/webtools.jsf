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
 * $Id: ConverterTagDefn.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Converter Tag Defn</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn#getConverterId <em>Converter Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getConverterTagDefn()
 * @model
 * @generated
 */
public interface ConverterTagDefn extends HandlerTagDefn
{
    /**
     * Returns the value of the '<em><b>Converter Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Converter Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Id</em>' attribute.
     * @see #setConverterId(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getConverterTagDefn_ConverterId()
     * @model
     * @generated
     */
    String getConverterId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn#getConverterId <em>Converter Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter Id</em>' attribute.
     * @see #getConverterId()
     * @generated
     */
    void setConverterId(String value);

} // ConverterTagDefn
