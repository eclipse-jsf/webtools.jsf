/**
 * <copyright>
 * </copyright>
 *
 * $Id: TraitTypesFactoryImpl.java,v 1.5.6.1 2010/07/01 23:25:04 rsrinivasan Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.*;

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
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static TraitTypesPackage getPackage() {
		return TraitTypesPackage.eINSTANCE;
	}

} //TraitTypesFactoryImpl
