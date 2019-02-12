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
 * $Id: AddTagMDFactory.java,v 1.1 2010/03/08 18:49:41 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.cm.addtagmd;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.cm.addtagmd.AddTagMDPackage
 * @generated
 */
public interface AddTagMDFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    AddTagMDFactory eINSTANCE = org.eclipse.jst.jsf.facelet.core.internal.cm.addtagmd.impl.AddTagMDFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Element Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Element Data</em>'.
     * @generated
     */
    ElementData createElementData();

    /**
     * Returns a new object of class '<em>Attribute Data</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Attribute Data</em>'.
     * @generated
     */
    AttributeData createAttributeData();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    AddTagMDPackage getAddTagMDPackage();

} //AddTagMDFactory
