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
 * $Id: ConstraintsFactory.java,v 1.1 2007/02/28 21:16:02 cbateman Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage
 * @generated
 */
public interface ConstraintsFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ConstraintsFactory eINSTANCE = org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Tag Id</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tag Id</em>'.
     * @generated
     */
    TagId createTagId();

    /**
     * Returns a new object of class '<em>Tag Set</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tag Set</em>'.
     * @generated
     */
    TagSet createTagSet();

    /**
     * Returns a new object of class '<em>Contains Tag Constraint</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Contains Tag Constraint</em>'.
     * @generated
     */
    ContainsTagConstraint createContainsTagConstraint();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ConstraintsPackage getConstraintsPackage();

} //ConstraintsFactory
