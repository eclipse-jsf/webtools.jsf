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
 * $Id: TraitTypesFactoryImpl.java,v 1.9 2010/04/27 17:40:10 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.BooleanValue;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.SetGenerator;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.StringValue;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesFactory;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TraitTypesFactoryImpl extends EFactoryImpl implements TraitTypesFactory {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * @return the factory
     * <!-- end-user-doc -->
	 * @generated NOT
	 */
    public static TraitTypesFactory init() {
		try {
			TraitTypesFactory theTraitTypesFactory = (TraitTypesFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jst.jsf.common.metadata/metadataTraitTypes.ecore");  //$NON-NLS-1$
			if (theTraitTypesFactory != null) {
				return theTraitTypesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TraitTypesFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TraitTypesFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TraitTypesPackage.LIST_OF_VALUES: return createListOfValues();
			case TraitTypesPackage.SET_GENERATOR: return createSetGenerator();
			case TraitTypesPackage.STRING_VALUE: return createStringValue();
			case TraitTypesPackage.BOOLEAN_VALUE: return createBooleanValue();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ListOfValues createListOfValues() {
		ListOfValuesImpl listOfValues = new ListOfValuesImpl();
		return listOfValues;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SetGenerator createSetGenerator() {
		SetGeneratorImpl setGenerator = new SetGeneratorImpl();
		return setGenerator;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringValue createStringValue() {
		StringValueImpl stringValue = new StringValueImpl();
		return stringValue;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanValue createBooleanValue() {
		BooleanValueImpl booleanValue = new BooleanValueImpl();
		return booleanValue;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TraitTypesPackage getTraitTypesPackage() {
		return (TraitTypesPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * @return the package
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static TraitTypesPackage getPackage() {
		return TraitTypesPackage.eINSTANCE;
	}

} //TraitTypesFactoryImpl
