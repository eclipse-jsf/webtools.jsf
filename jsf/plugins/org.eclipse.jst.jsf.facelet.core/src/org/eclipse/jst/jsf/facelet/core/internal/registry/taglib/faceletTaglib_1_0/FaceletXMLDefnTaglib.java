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
 * $Id: FaceletXMLDefnTaglib.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet XML Defn Taglib</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib#getTags <em>Tags</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib#getFunctions <em>Functions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFaceletXMLDefnTaglib()
 * @model
 * @generated
 */
public interface FaceletXMLDefnTaglib extends FaceletTaglibDefn
{
    /**
     * Returns the value of the '<em><b>Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Namespace</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Namespace</em>' attribute.
     * @see #setNamespace(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFaceletXMLDefnTaglib_Namespace()
     * @model
     * @generated
     */
    String getNamespace();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib#getNamespace <em>Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Namespace</em>' attribute.
     * @see #getNamespace()
     * @generated
     */
    void setNamespace(String value);

    /**
     * Returns the value of the '<em><b>Tags</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tags</em>' reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFaceletXMLDefnTaglib_Tags()
     * @model
     * @generated
     */
    EList<TagDefn> getTags();

    /**
     * Returns the value of the '<em><b>Functions</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Functions</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Functions</em>' reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#getFaceletXMLDefnTaglib_Functions()
     * @model
     * @generated
     */
    EList<FunctionDefn> getFunctions();

} // FaceletXMLDefnTaglib
