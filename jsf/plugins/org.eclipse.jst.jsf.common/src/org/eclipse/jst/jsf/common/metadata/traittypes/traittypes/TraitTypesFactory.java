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
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesPackage
 * @generated
 */
public interface TraitTypesFactory extends EFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TraitTypesFactory eINSTANCE = org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.TraitTypesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>List Of Values</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>List Of Values</em>'.
	 * @generated
	 */
	ListOfValues createListOfValues();

	/**
	 * Returns a new object of class '<em>Set Generator</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Set Generator</em>'.
	 * @generated
	 */
    SetGenerator createSetGenerator();

    /**
	 * Returns a new object of class '<em>String Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Value</em>'.
	 * @generated
	 */
	StringValue createStringValue();

	/**
	 * Returns a new object of class '<em>Boolean Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Value</em>'.
	 * @generated
	 */
	BooleanValue createBooleanValue();

				/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TraitTypesPackage getTraitTypesPackage();

} //TraitTypesFactory
