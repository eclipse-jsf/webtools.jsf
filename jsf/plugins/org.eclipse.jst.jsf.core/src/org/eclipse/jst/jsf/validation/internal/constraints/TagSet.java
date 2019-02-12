/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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
 * $Id: TagSet.java,v 1.1 2007/02/28 21:16:02 cbateman Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.TagSet#getTags <em>Tags</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getTagSet()
 * @model
 * @generated
 */
public interface TagSet extends EObject {
    /**
     * Returns the value of the '<em><b>Tags</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.validation.internal.constraints.TagId}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tags</em>' reference list.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getTagSet_Tags()
     * @model type="org.eclipse.jst.jsf.validation.internal.constraints.TagId"
     * @generated
     */
    EList getTags();

} // TagSet
